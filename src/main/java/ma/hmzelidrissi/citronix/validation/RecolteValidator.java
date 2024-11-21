package ma.hmzelidrissi.citronix.validation;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ma.hmzelidrissi.citronix.dto.request.RecolteDetailRequestDTO;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Arbre;
import ma.hmzelidrissi.citronix.domain.Saison;
import ma.hmzelidrissi.citronix.dto.request.RecolteRequestDTO;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.repository.ArbreRepository;
import ma.hmzelidrissi.citronix.repository.RecolteRepository;

@Component
@RequiredArgsConstructor
public class RecolteValidator {
  private final RecolteRepository recolteRepository;
  private final ArbreRepository arbreRepository;

  public void validate(RecolteRequestDTO request) {
    validateUniqueSaisonPerYear(request.dateRecolte(), request.saison());
    validateArbres(request);
    validateQuantities(request);
  }

  private void validateUniqueSaisonPerYear(LocalDate dateRecolte, Saison saison) {
    if (recolteRepository.existsByDateRecolteYearAndSaison(dateRecolte.getYear(), saison)) {
      throw new ValidationException(
          String.format(
              "Une récolte existe déjà pour la saison %s de l'année %d",
              saison, dateRecolte.getYear()));
    }
  }

  private void validateArbres(RecolteRequestDTO request) {
    Set<Long> arbreIds =
        request.details().stream()
            .map(RecolteDetailRequestDTO::arbreId)
            .collect(Collectors.toSet());

    List<Arbre> arbres = arbreRepository.findAllById(arbreIds);

    if (arbres.size() != arbreIds.size()) {
      throw new ValidationException("Certains arbres spécifiés n'existent pas");
    }

    arbres.forEach(
        arbre -> {
          int age = Period.between(arbre.getDatePlantation(), request.dateRecolte()).getYears();
          if (age > 20) {
            throw new ValidationException(
                String.format(
                    "L'arbre %s a dépassé sa durée de vie productive (20 ans)", arbre.getNom()));
          }
        });
  }

  private void validateQuantities(RecolteRequestDTO request) {
    request
        .details()
        .forEach(
            detail -> {
              Arbre arbre =
                  arbreRepository
                      .findById(detail.arbreId())
                      .orElseThrow(() -> new ValidationException("Arbre non trouvé"));

              double maxProductionParSaison = arbre.getStatus().getProductionParSaison();
              if (detail.quantite() > maxProductionParSaison) {
                throw new ValidationException(
                    String.format(
                        "La quantité récoltée (%f) dépasse la production maximale par saison (%f) pour l'arbre %s",
                        detail.quantite(), maxProductionParSaison, arbre.getNom()));
              }
            });
  }
}
