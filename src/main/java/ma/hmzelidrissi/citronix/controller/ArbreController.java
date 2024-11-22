package ma.hmzelidrissi.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.dto.request.ArbreRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ArbreResponseDTO;
import ma.hmzelidrissi.citronix.service.ArbreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/arbres")
@RequiredArgsConstructor
public class ArbreController {
  private final ArbreService arbreService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ArbreResponseDTO createArbre(@Valid @RequestBody ArbreRequestDTO request) {
    return arbreService.createArbre(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ArbreResponseDTO updateArbre(
      @PathVariable Long id, @Valid @RequestBody ArbreRequestDTO request) {
    return arbreService.updateArbre(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteArbre(@PathVariable Long id) {
    arbreService.deleteArbre(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ArbreResponseDTO getArbreById(@PathVariable Long id) {
    return arbreService.getArbreById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ArbreResponseDTO> getAllArbres() {
    return arbreService.getAllArbres();
  }

  @GetMapping("/champ/{champId}")
  @ResponseStatus(HttpStatus.OK)
  public List<ArbreResponseDTO> getArbresByChamp(@PathVariable Long champId) {
    return arbreService.getArbresByChamp(champId);
  }

  @PatchMapping("/{id}/status")
  @ResponseStatus(HttpStatus.OK)
  public ArbreResponseDTO updateArbreStatus(@PathVariable Long id) {
    return arbreService.updateArbreStatus(id);
  }
}
