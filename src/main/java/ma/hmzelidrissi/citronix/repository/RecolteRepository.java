package ma.hmzelidrissi.citronix.repository;

import ma.hmzelidrissi.citronix.domain.Recolte;
import ma.hmzelidrissi.citronix.domain.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {
  @Query(
      "SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Recolte r WHERE YEAR(r.dateRecolte) = :year AND r.saison = :saison")
  boolean existsByDateRecolteYearAndSaison(@Param("year") int year, @Param("saison") Saison saison);

  List<Recolte> findBySaison(Saison saison);

  @Query("SELECT r FROM Recolte r WHERE YEAR(r.dateRecolte) = :year")
  List<Recolte> findByYear(@Param("year") int year);
}
