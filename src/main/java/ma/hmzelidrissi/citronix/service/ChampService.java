package ma.hmzelidrissi.citronix.service;

import java.util.List;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ChampResponseDTO;

public interface ChampService {
  ChampResponseDTO createChamp(ChampRequestDTO dto);

  ChampResponseDTO updateChamp(Long id, ChampRequestDTO dto);

  void deleteChamp(Long id);

  ChampResponseDTO getChampById(Long id);

  List<ChampResponseDTO> getAllChamps();

  List<ChampResponseDTO> getChampsByFerme(Long fermeId);
}
