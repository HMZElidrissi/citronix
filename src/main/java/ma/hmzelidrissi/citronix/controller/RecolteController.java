package ma.hmzelidrissi.citronix.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Saison;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.RecolteResponseDTO;
import ma.hmzelidrissi.citronix.service.RecolteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recoltes")
@RequiredArgsConstructor
public class RecolteController {
  private final RecolteService recolteService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RecolteResponseDTO createRecolte(@Valid @RequestBody RecolteRequestDTO request) {
    return recolteService.createRecolte(request);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public RecolteResponseDTO getRecolteById(@PathVariable Long id) {
    return recolteService.getRecolteById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<RecolteResponseDTO> getAllRecoltes() {
    return recolteService.getAllRecoltes();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteRecolte(@PathVariable Long id) {
    recolteService.deleteRecolte(id);
  }

  @GetMapping("/saison/{saison}")
  @ResponseStatus(HttpStatus.OK)
  public List<RecolteResponseDTO> getRecolteBySaison(@PathVariable Saison saison) {
    return recolteService.getRecolteBySaison(saison);
  }

  @GetMapping("/year/{year}")
  @ResponseStatus(HttpStatus.OK)
  public List<RecolteResponseDTO> getRecolteByYear(@PathVariable int year) {
    return recolteService.getRecolteByYear(year);
  }
}
