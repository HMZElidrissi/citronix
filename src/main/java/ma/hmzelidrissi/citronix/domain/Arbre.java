package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

import lombok.*;

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
  private Champ champ;

  private ArbreStatus status;

  public int calculateAge() {
    return Period.between(this.getDatePlantation(), java.time.LocalDate.now()).getYears();
  }
}
