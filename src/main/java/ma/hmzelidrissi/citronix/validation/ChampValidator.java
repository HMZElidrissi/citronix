package ma.hmzelidrissi.citronix.validation;

import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.ChampRequestDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.exception.SuperficieException;
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
    if (!fermeRepository.existsById(dto.fermeId())) {
      throw new ResourceNotFoundException("Ferme", dto.fermeId());
    }
    Ferme ferme =
        fermeRepository
            .findById(dto.fermeId())
            .orElseThrow(() -> new ResourceNotFoundException("Ferme", dto.fermeId()));

    if (dto.superficie() > ferme.getSuperficie() / 2) {
      throw new SuperficieException(
          "Aucun champ ne peut dépasser 50% de la superficie totale de la ferme");
    }

    if (dto.superficie() > (ferme.getSuperficie() - ferme.getSumSuperficieChamps())) {
      throw new SuperficieException(
          "La somme des superficies des champs ne peut pas dépasser la superficie totale de la ferme");
    }
    Double nbrChamps = champRepository.countByFerme_Id(dto.fermeId());
    if (nbrChamps >= 10) {
      throw new SuperficieException("Une ferme ne peut pas contenir plus de 10 champs");
    }
  }
}
