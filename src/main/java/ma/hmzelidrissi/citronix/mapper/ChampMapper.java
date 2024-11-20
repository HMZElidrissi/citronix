package ma.hmzelidrissi.citronix.mapper;

import ma.hmzelidrissi.citronix.domain.Champ;
import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ChampResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChampMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "ferme", source = "fermeId", qualifiedByName = "fermeIdToFerme")
  Champ toEntity(ChampRequestDTO dto);

  @Named("fermeIdToFerme")
  default Ferme fermeIdToFerme(Long fermeId) {
    return Ferme.builder().id(fermeId).build();
  }

  ChampResponseDTO toDTO(Champ save);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "ferme", ignore = true)
  @Mapping(target = "id", ignore = true)
  void updateEntityFromDTO(ChampRequestDTO dto, @MappingTarget Champ entity);
}
