package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChampRequestDTO(
    @NotBlank(message = "Le nom du champ est obligatoire") String nom,
    @NotNull(message = "La superficie du champ est obligatoire")
        @Min(value = 1000, message = "La superficie du champ doit étre au minimum 1000 m²")
        Double superficie,
    @NotNull(message = "Un champ doit étre associé à une ferme obligatoirement") Long fermeId) {}
