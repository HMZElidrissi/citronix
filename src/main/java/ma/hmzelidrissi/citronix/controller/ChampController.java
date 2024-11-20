package ma.hmzelidrissi.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.ChampResponseDTO;
import ma.hmzelidrissi.citronix.service.ChampService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/champs")
@RequiredArgsConstructor
public class ChampController {
  private final ChampService champService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ChampResponseDTO createChamp(@Valid @RequestBody ChampRequestDTO request) {
    return champService.createChamp(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ChampResponseDTO updateChamp(
      @PathVariable Long id, @Valid @RequestBody ChampRequestDTO request) {
    return champService.updateChamp(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteChamp(@PathVariable Long id) {
    champService.deleteChamp(id);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ChampResponseDTO getChampById(@PathVariable Long id) {
    return champService.getChampById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ChampResponseDTO> getAllChamps() {
    return champService.getAllChamps();
  }

  @GetMapping("/ferme/{fermeId}")
  @ResponseStatus(HttpStatus.OK)
  public List<ChampResponseDTO> getChampsByFerme(@PathVariable Long fermeId) {
    return champService.getChampsByFerme(fermeId);
  }
}
