package ma.hmzelidrissi.citronix.dto.response;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record FermeResponseDTO(
    String nom,
    String localisation,
    Double superficie,
    Double sumSuperficieChamps,
    LocalDate dateCreation) {}
