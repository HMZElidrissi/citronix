package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;
import ma.hmzelidrissi.citronix.enums.ArbreStatus;

import java.time.LocalDate;

@Entity
@Table(name = "arbres")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "date_plantation", nullable = false)
    private LocalDate datePlantation;

    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private ma.hmzelidrissi.citronix.entities.Champ champ;

    private ArbreStatus status;
}
