package ma.hmzelidrissi.citronix.mapper;

import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FermeMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sumSuperficieChamps", ignore = true, defaultValue = "0.0")
  Ferme toEntity(FermeRequestDTO dto);

  FermeResponseDTO toDTO(Ferme ferme);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sumSuperficieChamps", ignore = true)
  void updateEntityFromDTO(FermeRequestDTO dto, @MappingTarget Ferme entity);
}
