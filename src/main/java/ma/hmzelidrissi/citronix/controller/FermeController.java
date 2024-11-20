package ma.hmzelidrissi.citronix.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.criteria.FermeSearchCriteria;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import ma.hmzelidrissi.citronix.service.FermeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/fermes")
@RequiredArgsConstructor
public class FermeController {
  private final FermeService fermeService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FermeResponseDTO createFerme(@Valid @RequestBody FermeRequestDTO request) {
    return fermeService.createFerme(request);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FermeResponseDTO updateFerme(
      @PathVariable Long id, @Valid @RequestBody FermeRequestDTO request) {
    return fermeService.updateFerme(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteFerme(@PathVariable Long id) {
    fermeService.deleteFerme(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Page<FermeResponseDTO> getFermes(
      @RequestParam(required = false) String nom,
      @RequestParam(required = false) String localisation,
      @RequestParam(required = false) Double superficie,
      @RequestParam(required = false) String dateCreation,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "nom") String sortBy,
      @RequestParam(defaultValue = "asc") String sortDirection) {
    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    FermeSearchCriteria criteria =
        FermeSearchCriteria.builder()
            .nom(nom)
            .localisation(localisation)
            .superficie(superficie)
            .dateCreation(LocalDate.parse(dateCreation))
            .build();
    return fermeService.getFermes(criteria, pageable);
  }
}
