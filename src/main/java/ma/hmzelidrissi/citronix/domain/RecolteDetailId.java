package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecolteDetailId implements Serializable {
    private Long recolteId;
    private Long arbreId;
}