package ma.hmzelidrissi.citronix.validation;

import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.repository.ChampRepository;
import ma.hmzelidrissi.citronix.repository.FermeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChampValidator {
  private final FermeRepository fermeRepository;
  private final ChampRepository champRepository;

  public void validate(ChampRequestDTO dto) {
    validateFermeExists(dto);
    Ferme ferme = getFerme(dto);
    validateSuperficie(dto, ferme);
    validateNbrChamps(dto.fermeId());
  }

  private void validateNbrChamps(Long fermeId) {
    Double nbrChamps = champRepository.countByFermeId(fermeId);
    if (nbrChamps >= 10) {
      throw new ValidationException("Une ferme ne peut pas contenir plus de 10 champs");
    }
  }

  private static void validateSuperficie(ChampRequestDTO dto, Ferme ferme) {
    if (dto.superficie() > ferme.getSuperficie() / 2) {
      throw new ValidationException(
          "Aucun champ ne peut dépasser 50% de la superficie totale de la ferme");
    }

    if (dto.superficie() > (ferme.getSuperficie() - ferme.getSumSuperficieChamps())) {
      throw new ValidationException(
          "La somme des superficies des champs ne peut pas dépasser la superficie totale de la ferme");
    }
  }

  private Ferme getFerme(ChampRequestDTO dto) {
    return fermeRepository
        .findById(dto.fermeId())
        .orElseThrow(() -> new ResourceNotFoundException("Ferme", dto.fermeId()));
  }

  private void validateFermeExists(ChampRequestDTO dto) {
    if (!fermeRepository.existsById(dto.fermeId())) {
      throw new ResourceNotFoundException("Ferme", dto.fermeId());
    }
  }
}
