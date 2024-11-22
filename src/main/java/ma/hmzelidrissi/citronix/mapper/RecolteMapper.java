package ma.hmzelidrissi.citronix.mapper;

import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.RecolteResponseDTO;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    uses = {RecolteDetailMapper.class})
public interface RecolteMapper {
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "quantiteTotale", ignore = true)
  @Mapping(target = "recolteDetails", ignore = true)
  Recolte toEntity(RecolteRequestDTO dto);

  @Mapping(target = "details", source = "recolteDetails")
  RecolteResponseDTO toDTO(Recolte recolte);
}
