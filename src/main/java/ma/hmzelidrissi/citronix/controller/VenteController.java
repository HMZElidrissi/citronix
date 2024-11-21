package ma.hmzelidrissi.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.dto.request.VenteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.VenteResponseDTO;
import ma.hmzelidrissi.citronix.service.VenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ventes")
@RequiredArgsConstructor
public class VenteController {
  private final VenteService venteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VenteResponseDTO createVente(@Valid @RequestBody VenteRequestDTO request) {
    return venteService.createVente(request);
  }

  @GetMapping("/{id}")
  public VenteResponseDTO getVenteById(@PathVariable Long id) {
    return venteService.getVenteById(id);
  }

  @GetMapping
  public List<VenteResponseDTO> getAllVentes() {
    return venteService.getAllVentes();
  }

  @GetMapping("/recolte/{recolteId}")
  public List<VenteResponseDTO> getVentesByRecolte(@PathVariable Long recolteId) {
    return venteService.getVentesByRecolte(recolteId);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteVente(@PathVariable Long id) {
    venteService.deleteVente(id);
  }
}
