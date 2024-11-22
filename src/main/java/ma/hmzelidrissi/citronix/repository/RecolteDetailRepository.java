package ma.hmzelidrissi.citronix.repository;

import ma.hmzelidrissi.citronix.domain.RecolteDetail;
import ma.hmzelidrissi.citronix.domain.RecolteDetailId;
import ma.hmzelidrissi.citronix.domain.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, RecolteDetailId> {
  List<RecolteDetail> findByRecolteId(Long recolteId);

  List<RecolteDetail> findByArbreId(Long arbreId);

  @Query(
      "SELECT rd FROM RecolteDetail rd "
          + "JOIN rd.recolte r "
          + "WHERE r.saison = :saison AND YEAR(r.dateRecolte) = :year")
  List<RecolteDetail> findBySaisonAndYear(@Param("saison") Saison saison, @Param("year") int year);
}
