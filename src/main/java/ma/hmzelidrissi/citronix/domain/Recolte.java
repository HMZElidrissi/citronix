package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "recoltes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recolte {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "quantite_totale", nullable = false)
  private Double quantiteTotale;

  @Column(name = "date_recolte", nullable = false)
  private LocalDate dateRecolte;

  @Enumerated(EnumType.STRING)
  @Column(name = "saision", nullable = false)
  private Saison saison;
}
