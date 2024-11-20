package ma.hmzelidrissi.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.criteria.FermeSearchCriteria;
import ma.hmzelidrissi.citronix.criteria.FermeSpecificationBuilder;
import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.FermeMapper;
import ma.hmzelidrissi.citronix.repository.FermeRepository;
import ma.hmzelidrissi.citronix.service.FermeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FermeServiceImpl implements FermeService {
  private final FermeRepository fermeRepository;
  private final FermeMapper fermeMapper;
  private final FermeSpecificationBuilder fermeSpecificationBuilder;

  @Override
  public FermeResponseDTO createFerme(FermeRequestDTO dto) {
    Ferme ferme = fermeMapper.toEntity(dto);
    return fermeMapper.toDTO(fermeRepository.save(ferme));
  }

  @Override
  public FermeResponseDTO updateFerme(Long id, FermeRequestDTO dto) {
    Ferme existingFerme =
        fermeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ferme", id));

    fermeMapper.updateEntityFromDTO(dto, existingFerme);

    return fermeMapper.toDTO(fermeRepository.save(existingFerme));
  }

  @Override
  public void deleteFerme(Long id) {
    if (!fermeRepository.existsById(id)) {
      throw new ResourceNotFoundException("Ferme", id);
    }
    fermeRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<FermeResponseDTO> getFermes(FermeSearchCriteria criteria, Pageable pageable) {
    Specification<Ferme> specification = fermeSpecificationBuilder.build(criteria);
    return fermeRepository.findAll(specification, pageable).map(fermeMapper::toDTO);
  }
}
