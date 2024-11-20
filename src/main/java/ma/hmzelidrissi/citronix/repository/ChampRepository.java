package ma.hmzelidrissi.citronix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.hmzelidrissi.citronix.domain.Champ;

public interface ChampRepository extends JpaRepository<Champ, Long> {
  List<Champ> findByFerme_Id(Long fermeId);

  Double countByFerme_Id(Long fermeId);
}
