package ma.hmzelidrissi.citronix.criteria;

import jakarta.persistence.criteria.Predicate;
import ma.hmzelidrissi.citronix.domain.Ferme;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FermeSpecificationBuilder {
  public Specification<Ferme> build(FermeSearchCriteria criteria) {
    return ((root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (criteria.nom() != null) {
        predicates.add(criteriaBuilder.like(root.get("nom"), "%" + criteria.nom() + "%"));
      }
      if (criteria.localisation() != null) {
        predicates.add(
            criteriaBuilder.like(root.get("localisation"), "%" + criteria.localisation() + "%"));
      }
      if (criteria.superficie() != null) {
        predicates.add(criteriaBuilder.equal(root.get("superficie"), criteria.superficie()));
      }
      if (criteria.dateCreation() != null) {
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(root.get("dateCreation"), criteria.dateCreation()));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    });
  }
}
