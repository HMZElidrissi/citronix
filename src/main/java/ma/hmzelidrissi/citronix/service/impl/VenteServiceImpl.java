package ma.hmzelidrissi.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Vente;
import ma.hmzelidrissi.citronix.dto.request.VenteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.VenteResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.VenteMapper;
import ma.hmzelidrissi.citronix.repository.VenteRepository;
import ma.hmzelidrissi.citronix.service.VenteService;
import ma.hmzelidrissi.citronix.validation.VenteValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VenteServiceImpl implements VenteService {
  private final VenteRepository venteRepository;
  private final VenteMapper venteMapper;
  private final VenteValidator venteValidator;

  @Override
  public VenteResponseDTO createVente(VenteRequestDTO request) {
    venteValidator.validate(request);
    Vente vente = venteMapper.toEntity(request);
    return venteMapper.toDTO(venteRepository.save(vente));
  }

  @Override
  public VenteResponseDTO getVenteById(Long id) {
    return venteRepository
        .findById(id)
        .map(venteMapper::toDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Vente", id));
  }

  @Override
  public List<VenteResponseDTO> getAllVentes() {
    return venteRepository.findAll().stream().map(venteMapper::toDTO).toList();
  }

  @Override
  public List<VenteResponseDTO> getVentesByRecolte(Long recolteId) {
    return venteRepository.findByRecolteId(recolteId).stream().map(venteMapper::toDTO).toList();
  }

  @Override
  public void deleteVente(Long id) {
    if (!venteRepository.existsById(id)) {
      throw new ResourceNotFoundException("Vente", id);
    }
    venteRepository.deleteById(id);
  }
}
