package ma.hmzelidrissi.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "date_vente", nullable = false)
  private LocalDate dateVente;

  @Column(name = "quantite", nullable = false)
  private Double quantite;

  @Column(name = "prix_unitaire", nullable = false)
  private Double prix_unitaire;

  @ManyToOne
  @JoinColumn(name = "recolte_id", nullable = false)
  private Recolte recolte;

  @Column(name = "client", nullable = false)
  private String client;
}
