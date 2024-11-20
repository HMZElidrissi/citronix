package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "champs")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "superficie", nullable = false)
    private Double superficie;

    @ManyToOne
    @JoinColumn(name = "ferme_id", nullable = false)
    private ma.hmzelidrissi.citronix.entities.Ferme ferme;

//    @ManyToOne
//    @JoinColumn(name = "culture_id", nullable = false)
//    private Culture culture;
}
