package ma.hmzelidrissi.citronix.validation;

import lombok.RequiredArgsConstructor;
import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.dto.request.VenteRequestDTO;
import ma.hmzelidrissi.citronix.exception.ValidationException;
import ma.hmzelidrissi.citronix.repository.RecolteRepository;
import ma.hmzelidrissi.citronix.repository.VenteRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VenteValidator {
  private final RecolteRepository recolteRepository;
  private final VenteRepository venteRepository;

  public void validate(VenteRequestDTO request) {
    Recolte recolte =
        recolteRepository
            .findById(request.recolteId())
            .orElseThrow(() -> new ValidationException("Récolte non trouvée"));

    double totalVendu = venteRepository.countByRecolteId(request.recolteId());
    if (totalVendu + request.quantite() > recolte.getQuantiteTotale()) {
      throw new ValidationException("Quantité demandée dépasse le stock disponible");
    }
  }
}
