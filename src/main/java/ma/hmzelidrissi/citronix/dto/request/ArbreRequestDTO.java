package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record ArbreRequestDTO(
    @NotBlank(message = "Le nom de l'arbre est obligatoire") String nom,
    @NotNull(message = "La date de plantation est obligatoire")
        @PastOrPresent(message = "La date de plantation ne peut pas être dans le futur")
        LocalDate datePlantation,
    @NotNull(message = "Un arbre doit être associé à un champ") Long champId) {}
