package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recolte_details")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecolteDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "quantite", nullable = false)
  private Double quantite;

  @ManyToOne
  @JoinColumn(name = "recolte_id", nullable = false)
  private ma.hmzelidrissi.citronix.entities.Recolte recolte;

  @ManyToOne
  @JoinColumn(name = "arbre_id", nullable = false)
  private Arbre arbre;
}
