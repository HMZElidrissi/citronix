package ma.hmzelidrissi.citronix.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "fermes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "localisation", nullable = false)
    private String localisation;

    @Column(name = "superficie", nullable = false)
    private Double superficie;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;
}