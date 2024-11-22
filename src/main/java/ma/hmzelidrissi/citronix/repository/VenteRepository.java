package ma.hmzelidrissi.citronix.repository;

import ma.hmzelidrissi.citronix.domain.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {
  List<Vente> findByRecolteId(Long recolteId);

  @Query("SELECT COALESCE(SUM(v.quantite), 0) FROM Vente v WHERE v.recolte.id = :recolteId")
  double sumQuantiteByRecolteId(@Param("recolteId") Long recolteId);
}
