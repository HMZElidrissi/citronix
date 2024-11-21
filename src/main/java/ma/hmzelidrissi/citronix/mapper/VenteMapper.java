package ma.hmzelidrissi.citronix.mapper;

import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.domain.Vente;
import ma.hmzelidrissi.citronix.dto.request.VenteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.VenteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface VenteMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "recolte", source = "recolteId", qualifiedByName = "recolteIdToRecolte")
  Vente toEntity(VenteRequestDTO dto);

  @Named("recolteIdToRecolte")
  default Recolte recolteIdToRecolte(Long recolteId) {
    return Recolte.builder().id(recolteId).build();
  }

  @Mapping(target = "revenu", expression = "java(vente.getQuantite() * vente.getPrix_unitaire())")
  @Mapping(target = "recolteId", source = "recolte.id")
  VenteResponseDTO toDTO(Vente vente);
}
