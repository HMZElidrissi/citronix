package ma.hmzelidrissi.citronix.service;

import ma.hmzelidrissi.citronix.dto.request.VenteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.VenteResponseDTO;

import java.util.List;

public interface VenteService {
  VenteResponseDTO createVente(VenteRequestDTO request);

  VenteResponseDTO getVenteById(Long id);

  List<VenteResponseDTO> getAllVentes();

  List<VenteResponseDTO> getVentesByRecolte(Long recolteId);

  void deleteVente(Long id);
}
