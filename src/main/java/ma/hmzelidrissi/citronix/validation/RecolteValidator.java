package ma.hmzelidrissi.citronix.validation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Arbre;
import ma.hmzelidrissi.citronix.domain.Champ;
import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.dto.request.RecolteDetailRequestDTO;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.repository.ArbreRepository;
import ma.hmzelidrissi.citronix.repository.RecolteRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecolteValidator {
  private final RecolteRepository recolteRepository;
  private final ArbreRepository arbreRepository;

  public void validate(RecolteRequestDTO request) {
    validateOneRecoltePerSaison(request);

    // Verifier qu'un seul récolte par saison
    validateRecolteDetails(request.details());
  }

  private void validateRecolteDetails(Set<RecolteDetailRequestDTO> details) {
    // Verfier l'existence des arbres
    Set<Long> arbreIds =
        details.stream().map(RecolteDetailRequestDTO::arbreId).collect(Collectors.toSet());
    if (arbreIds.size() != details.size()) {
      throw new ValidationException("Les arbres doivent être uniques");
    }
    if (!arbreRepository.existsByIdIn(arbreIds.stream().toList())) {
      throw new ValidationException("Les arbres n'existent pas");
    }

    // Chaque champ ne peut être associé qu'à une seule récolte par saison
    List<Long> champsIds =
        arbreRepository.findAllById(arbreIds).stream()
            .map(Arbre::getChamp)
            .map(Champ::getId)
            .toList();
    if (champsIds.size() > 2) {
      throw new ValidationException(
          "Chaque champ ne peut être associé qu'à une seule récolte par saison");
    }
  }

  private void validateOneRecoltePerSaison(RecolteRequestDTO request) {
    if (recolteRepository.existsByDateRecolteYearAndSaison(
        request.dateRecolte().getYear(), request.saison())) {
      throw new ValidationException("Un seul récolte par saison est autorisé");
    }
  }
}
