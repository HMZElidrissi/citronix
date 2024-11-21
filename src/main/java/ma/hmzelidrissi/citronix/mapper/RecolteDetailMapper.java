package ma.hmzelidrissi.citronix.mapper;

import ma.hmzelidrissi.citronix.domain.Arbre;
import ma.hmzelidrissi.citronix.domain.RecolteDetail;
import ma.hmzelidrissi.citronix.domain.RecolteDetailId;
import ma.hmzelidrissi.citronix.dto.request.RecolteDetailRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.RecolteDetailResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RecolteDetailMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "recolte", ignore = true)
  @Mapping(target = "arbre", source = "arbreId", qualifiedByName = "arbreIdToArbre")
  RecolteDetail toEntity(RecolteDetailRequestDTO dto);

  @Named("arbreIdToArbre")
  default Arbre arbreIdToArbre(Long arbreId) {
    return Arbre.builder().id(arbreId).build();
  }

  @Mapping(target = "arbreId", source = "arbre.id")
  @Mapping(target = "arbreNom", source = "arbre.nom")
  RecolteDetailResponseDTO toDTO(RecolteDetail recolteDetail);
}
