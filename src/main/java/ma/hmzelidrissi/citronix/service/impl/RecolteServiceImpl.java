package ma.hmzelidrissi.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.domain.RecolteDetail;
import ma.hmzelidrissi.citronix.domain.RecolteDetailId;
import ma.hmzelidrissi.citronix.domain.Saison;
import ma.hmzelidrissi.citronix.dto.request.RecolteDetailRequestDTO;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.RecolteResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.RecolteMapper;
import ma.hmzelidrissi.citronix.mapper.RecolteDetailMapper;
import ma.hmzelidrissi.citronix.repository.RecolteRepository;
import ma.hmzelidrissi.citronix.repository.RecolteDetailRepository;
import ma.hmzelidrissi.citronix.service.RecolteService;
import ma.hmzelidrissi.citronix.validation.RecolteValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecolteServiceImpl implements RecolteService {
  private final RecolteRepository recolteRepository;
  private final RecolteDetailRepository recolteDetailRepository;
  private final RecolteMapper recolteMapper;
  private final RecolteDetailMapper recolteDetailMapper;
  private final RecolteValidator recolteValidator;

  @Override
  public RecolteResponseDTO createRecolte(RecolteRequestDTO request) {
    recolteValidator.validate(request);

    Recolte recolte = recolteMapper.toEntity(request);
    recolte = recolteRepository.save(recolte);

    double quantiteTotale = 0;

    for (var detailDTO : request.details()) {
      RecolteDetail detail = recolteDetailMapper.toEntity(detailDTO);
      detail.setRecolte(recolte);
      detail.setId(new RecolteDetailId(recolte.getId(), detailDTO.arbreId()));
      recolteDetailRepository.save(detail);

      quantiteTotale += detail.getQuantite();
    }

    recolte.setQuantiteTotale(quantiteTotale);
    recolte = recolteRepository.save(recolte);

    return recolteMapper.toDTO(recolte);
  }

  @Override
  public RecolteResponseDTO getRecolteById(Long id) {
    return recolteRepository
        .findById(id)
        .map(recolteMapper::toDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));
  }

  @Override
  public List<RecolteResponseDTO> getAllRecoltes() {
    return recolteRepository.findAll().stream().map(recolteMapper::toDTO).toList();
  }

  @Override
  public void deleteRecolte(Long id) {
    if (!recolteRepository.existsById(id)) {
      throw new ResourceNotFoundException("Recolte", id);
    }
    recolteRepository.deleteById(id);
  }

  @Override
  public List<RecolteResponseDTO> getRecolteBySaison(Saison saison) {
    return recolteRepository.findBySaison(saison).stream().map(recolteMapper::toDTO).toList();
  }

  @Override
  public List<RecolteResponseDTO> getRecolteByYear(int year) {
    return recolteRepository.findByYear(year).stream().map(recolteMapper::toDTO).toList();
  }
}
