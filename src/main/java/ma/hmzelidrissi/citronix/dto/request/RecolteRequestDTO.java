package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ma.hmzelidrissi.citronix.domain.Saison;
import java.time.LocalDate;
import java.util.List;

public record RecolteRequestDTO(
    @NotNull(message = "La date de récolte est obligatoire") LocalDate dateRecolte,
    @NotNull(message = "La saison est obligatoire") Saison saison,
    @NotEmpty(message = "Les détails de récolte sont obligatoires")
        List<@Valid RecolteDetailRequestDTO> details) {}
