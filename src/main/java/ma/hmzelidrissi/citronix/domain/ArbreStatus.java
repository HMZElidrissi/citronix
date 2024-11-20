package ma.hmzelidrissi.citronix.enums;

import lombok.Getter;

@Getter
public enum ArbreStatus {
    JEUNE("< 3 ans", 2.5),
    MATURE("3-10 ans", 12.0),
    VIEUX("> 10 ans", 20.0);

    private final String description;
    private final double productionParSaison;

    ArbreStatus(String description, double productionParSaison) {
        this.description = description;
        this.productionParSaison = productionParSaison;
    }
}