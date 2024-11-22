package ma.hmzelidrissi.citronix.repository;

import java.util.List;
import ma.hmzelidrissi.citronix.domain.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArbreRepository extends JpaRepository<Arbre, Long> {
  long countByChampId(Long champId);

  List<Arbre> findByChampId(Long champId);

  boolean existsByIdIn(List<Long> ids);
}
