package ma.hmzelidrissi.citronix.dto.response;

import lombok.Builder;

@Builder
public record ChampResponseDTO(String nom, Double superficie) {}
