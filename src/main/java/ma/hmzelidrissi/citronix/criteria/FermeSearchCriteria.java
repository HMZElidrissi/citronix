package ma.hmzelidrissi.citronix.criteria;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FermeSearchCriteria(
    String nom, String localisation, Double superficie, LocalDate dateCreation) {}
