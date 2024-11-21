package ma.hmzelidrissi.citronix.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Champ;
import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ChampResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.ChampMapper;
import ma.hmzelidrissi.citronix.repository.ChampRepository;
import ma.hmzelidrissi.citronix.repository.FermeRepository;
import ma.hmzelidrissi.citronix.service.ChampService;
import ma.hmzelidrissi.citronix.validation.ChampValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChampServiceImpl implements ChampService {
  private final ChampRepository champRepository;
  private final ChampMapper champMapper;
  private final ChampValidator champValidator;
  private final FermeRepository fermeRepository;

  @Override
  public ChampResponseDTO createChamp(ChampRequestDTO dto) {
    champValidator.validate(dto);
    Champ champ = champMapper.toEntity(dto);
    Champ savedChamp = champRepository.save(champ);
    Ferme ferme =
        fermeRepository
            .findById(dto.fermeId())
            .orElseThrow(() -> new ResourceNotFoundException("Ferme", dto.fermeId()));
    ferme.setSumSuperficieChamps(ferme.getSumSuperficieChamps() + savedChamp.getSuperficie());
    fermeRepository.save(ferme);
    return champMapper.toDTO(savedChamp);
  }

  @Override
  public ChampResponseDTO updateChamp(Long id, ChampRequestDTO dto) {
    Champ existingChamp =
        champRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Champ", id));

    champMapper.updateEntityFromDTO(dto, existingChamp);

    return champMapper.toDTO(champRepository.save(existingChamp));
  }

  @Override
  public void deleteChamp(Long id) {
    if (!champRepository.existsById(id)) {
      throw new ResourceNotFoundException("Champ", id);
    }
    champRepository.deleteById(id);
  }

  @Override
  public ChampResponseDTO getChampById(Long id) {
    return champRepository
        .findById(id)
        .map(champMapper::toDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Champ", id));
  }

  @Override
  public List<ChampResponseDTO> getAllChamps() {
    return champRepository.findAll().stream().map(champMapper::toDTO).toList();
  }

  @Override
  public List<ChampResponseDTO> getChampsByFerme(Long fermeId) {
    return champRepository.findByFerme_Id(fermeId).stream().map(champMapper::toDTO).toList();
  }
}
