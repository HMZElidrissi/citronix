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
  @EmbeddedId private RecolteDetailId id;

  @Column(name = "quantite", nullable = false)
  private Double quantite;

  @ManyToOne
  @MapsId("recolteId")
  @JoinColumn(name = "recolte_id")
  private Recolte recolte;

  @ManyToOne
  @MapsId("arbreId")
  @JoinColumn(name = "arbre_id")
  private Arbre arbre;
}
