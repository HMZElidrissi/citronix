package ma.hmzelidrissi.citronix.dto.response;

import ma.hmzelidrissi.citronix.domain.ArbreStatus;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ArbreResponseDTO(
    Long id,
    String nom,
    LocalDate datePlantation,
    int age,
    ArbreStatus status,
    double productiviteParSaison,
    double productiviteAnnuelle) {}
