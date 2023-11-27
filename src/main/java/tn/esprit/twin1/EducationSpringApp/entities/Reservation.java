package tn.esprit.twin1.EducationSpringApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Reservation")
public class Reservation {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idReservation;

    @Column(name = "anneUniversitaire",nullable = false)
    private Date anneUniversitaire;

    @ManyToOne(cascade = CascadeType.ALL)
    Chambre chambre ;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Etudiant> etudiantSet;
}
