package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "champs")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Champ {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "nom", nullable = false)
  private String nom;

  @Column(name = "superficie", nullable = false)
  private Double superficie;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ferme_id", nullable = false)
  @ToString.Exclude
  private Ferme ferme;
}
