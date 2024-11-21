package ma.hmzelidrissi.citronix.service;

import ma.hmzelidrissi.citronix.domain.Saison;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.RecolteResponseDTO;
import java.util.List;

public interface RecolteService {
  RecolteResponseDTO createRecolte(RecolteRequestDTO request);

  RecolteResponseDTO getRecolteById(Long id);

  List<RecolteResponseDTO> getAllRecoltes();

  void deleteRecolte(Long id);

  List<RecolteResponseDTO> getRecolteBySaison(Saison saison);

  List<RecolteResponseDTO> getRecolteByYear(int year);
}
