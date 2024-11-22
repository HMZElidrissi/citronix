package ma.hmzelidrissi.citronix.dto.response;

import lombok.Builder;

@Builder
public record RecolteDetailResponseDTO(Long arbreId, String arbreNom, Double quantite) {}
