package ma.hmzelidrissi.citronix.repository;

import java.util.List;
import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.domain.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {
  @Query(
      "SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END "
          + "FROM Recolte r WHERE YEAR(r.dateRecolte) = :year AND r.saison = :saison")
  boolean existsByDateRecolteYearAndSaison(int year, Saison saison);

  List<Recolte> findBySaison(Saison saison);

  @Query("SELECT r FROM Recolte r WHERE YEAR(r.dateRecolte) = :year")
  List<Recolte> findByYear(int year);
}
