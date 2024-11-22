package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecolteDetailId implements Serializable {
  @Column(name = "recolte_id")
  private Long recolteId;

  @Column(name = "arbre_id")
  private Long arbreId;
}
