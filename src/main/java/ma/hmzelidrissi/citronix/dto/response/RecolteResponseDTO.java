package ma.hmzelidrissi.citronix.dto.response;

import ma.hmzelidrissi.citronix.domain.Saison;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record RecolteResponseDTO(
    Long id,
    LocalDate dateRecolte,
    Saison saison,
    Double quantiteTotale,
    List<RecolteDetailResponseDTO> details) {}
