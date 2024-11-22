package ma.hmzelidrissi.citronix.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Arbre;
import ma.hmzelidrissi.citronix.domain.ArbreStatus;
import ma.hmzelidrissi.citronix.dto.request.ArbreRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ArbreResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.mapper.ArbreMapper;
import ma.hmzelidrissi.citronix.repository.ArbreRepository;
import ma.hmzelidrissi.citronix.service.ArbreService;
import ma.hmzelidrissi.citronix.validation.ArbreValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArbreServiceImpl implements ArbreService {
  private final ArbreRepository arbreRepository;
  private final ArbreMapper arbreMapper;
  private final ArbreValidator arbreValidator;

  @Override
  public ArbreResponseDTO createArbre(ArbreRequestDTO request) {
    arbreValidator.validate(request);
    Arbre arbre = arbreMapper.toEntity(request);
    arbre.setStatus(determineArbreStatus(arbre));
    return arbreMapper.toDTO(arbreRepository.save(arbre));
  }

  @Override
  public ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO request) {
    Arbre existingArbre =
        arbreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Arbre", id));

    arbreValidator.validate(request);
    arbreMapper.updateEntityFromDTO(request, existingArbre);
    existingArbre.setStatus(determineArbreStatus(existingArbre));

    return arbreMapper.toDTO(arbreRepository.save(existingArbre));
  }

  @Override
  public void deleteArbre(Long id) {
    if (!arbreRepository.existsById(id)) {
      throw new ResourceNotFoundException("Arbre", id);
    }
    arbreRepository.deleteById(id);
  }

  @Override
  public ArbreResponseDTO getArbreById(Long id) {
    return arbreRepository
        .findById(id)
        .map(arbreMapper::toDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Arbre", id));
  }

  @Override
  public List<ArbreResponseDTO> getAllArbres() {
    return arbreRepository.findAll().stream().map(arbreMapper::toDTO).toList();
  }

  @Override
  public List<ArbreResponseDTO> getArbresByChamp(Long champId) {
    return arbreRepository.findByChampId(champId).stream().map(arbreMapper::toDTO).toList();
  }

  @Override
  public ArbreResponseDTO updateArbreStatus(Long id) {
    Arbre arbre =
        arbreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Arbre", id));

    arbre.setStatus(determineArbreStatus(arbre));
    return arbreMapper.toDTO(arbreRepository.save(arbre));
  }

  private ArbreStatus determineArbreStatus(Arbre arbre) {
    int age = arbre.calculateAge();

    if (age > 20) {
      throw new ValidationException(
          "L'arbre a dépassé sa durée de vie productive maximale de 20 ans");
    }

    if (age < 3) {
      return ArbreStatus.JEUNE;
    } else if (age <= 10) {
      return ArbreStatus.MATURE;
    } else {
      return ArbreStatus.VIEUX;
    }
  }
}
