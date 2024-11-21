package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RecolteDetailRequestDTO(
    @NotNull(message = "L'identifiant de l'arbre est obligatoire") Long arbreId,
    @NotNull(message = "La quantité récoltée est obligatoire")
        @Positive(message = "La quantité récoltée doit être positive")
        Double quantite) {}
