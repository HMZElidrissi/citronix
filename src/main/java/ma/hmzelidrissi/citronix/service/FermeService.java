package ma.hmzelidrissi.citronix.service;

import ma.hmzelidrissi.citronix.criteria.FermeSearchCriteria;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FermeService {
  FermeResponseDTO createFerme(FermeRequestDTO dto);

  FermeResponseDTO updateFerme(Long id, FermeRequestDTO dto);

  void deleteFerme(Long id);

  Page<FermeResponseDTO> getFermes(FermeSearchCriteria criteria, Pageable pageable);
}
