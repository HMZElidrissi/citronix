package ma.hmzelidrissi.citronix.service;

import ma.hmzelidrissi.citronix.dto.request.ArbreRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ArbreResponseDTO;
import java.util.List;

public interface ArbreService {
  ArbreResponseDTO createArbre(ArbreRequestDTO request);

  ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO request);

  void deleteArbre(Long id);

  ArbreResponseDTO getArbreById(Long id);

  List<ArbreResponseDTO> getAllArbres();

  List<ArbreResponseDTO> getArbresByChamp(Long champId);

  ArbreResponseDTO updateArbreStatus(Long id);
}
