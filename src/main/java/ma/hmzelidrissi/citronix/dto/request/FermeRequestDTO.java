package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record FermeRequestDTO(
    @NotBlank(message = "Le nom est obligatoire") String nom,
    @NotBlank(message = "La localisation est obligatoire") String localisation,
    @NotNull(message = "La superficie est obligatoire")
        @Positive(message = "La superficie doit être positive")
        @DecimalMin(value = "0.1", message = "La superficie doit être supérieure à 0")
        Double superficie,
    @NotNull(message = "La date de création est obligatoire, format: yyyy-MM-dd")
        @PastOrPresent(message = "La date de création doit être passée ou présente")
        LocalDate dateCreation) {}
