package ma.hmzelidrissi.citronix.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record VenteRequestDTO(
    @NotNull(message = "La date de vente est obligatoire") LocalDate dateVente,
    @Positive(message = "La quantité vendue doit être positive") Double quantite,
    @Positive(message = "Le prix unitaire doit être positif") Double prixUnitaire,
    @NotNull(message = "L'identifiant de la récolte est obligatoire") Long recolteId,
    @NotBlank(message = "Le client est obligatoire") String client) {}
