package ma.hmzelidrissi.citronix.validation;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.dto.request.ArbreRequestDTO;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.repository.ArbreRepository;
import ma.hmzelidrissi.citronix.repository.ChampRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class ArbreValidator {
  private final ChampRepository champRepository;
  private final ArbreRepository arbreRepository;
  private static final int MAX_ARBRES_PAR_HECTARE = 100;

  public void validate(ArbreRequestDTO request) {
    validatePlantationPeriod(request.datePlantation());
    validateChampCapacity(request.champId());
  }

  private void validatePlantationPeriod(LocalDate datePlantation) {
    Month plantationMonth = datePlantation.getMonth();
    if (plantationMonth.getValue() < Month.MARCH.getValue()
        || plantationMonth.getValue() > Month.MAY.getValue()) {
      throw new ValidationException("Les arbres ne peuvent être plantés qu'entre mars et mai");
    }
  }

  private void validateChampCapacity(Long champId) {
    var champ =
        champRepository
            .findById(champId)
            .orElseThrow(() -> new ValidationException("Le champ spécifié n'existe pas"));

    long currentTreeCount = arbreRepository.countByChampId(champId);
    double maxTreesAllowed =
        (champ.getSuperficie() / 1000)
            * MAX_ARBRES_PAR_HECTARE; // we divide by 1000 to convert m² to ha

    if (currentTreeCount >= maxTreesAllowed) {
      throw new ValidationException(
          String.format(
              "Le champ a atteint sa capacité maximale de %d arbres", (int) maxTreesAllowed));
    }
  }
}
