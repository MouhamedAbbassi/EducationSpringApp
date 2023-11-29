package tn.esprit.twin1.EducationSpringApp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Table(name = "Chambre")
public class Chambre {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idChambre;
    @Column(name = "numeroChambre",nullable = false)
    private String numeroChambre;
    @Enumerated(EnumType.STRING)
    @Column(name="typeChambre")
    private TypeChambre typeChambre;

    @ManyToOne
    @JsonIgnore
    Bloc bloc ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="chambre")
    @JsonIgnore
    private Set<Reservation> reservationSet;
}
