package ma.hmzelidrissi.citronix.dto.response;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record VenteResponseDTO(
    Long id,
    LocalDate dateVente,
    Double quantite,
    Double prixUnitaire,
    Double revenu,
    String client,
    Long recolteId) {}
