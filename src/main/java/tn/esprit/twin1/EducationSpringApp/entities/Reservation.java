package tn.esprit.twin1.EducationSpringApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
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
    private Long idReservation;

    @Temporal(TemporalType.DATE)
    @Column(name = "anneUniversitaire",nullable = false)
    private Date anneeUniversaire;

    @ManyToOne
    Chambre chambre ;



    @OneToOne
    private Etudiant etudiant;

    @ElementCollection(targetClass = choixReservation.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "reservation_choix", joinColumns = @JoinColumn(name = "reservation_id"))
    @Column(name = "choix_reservation")
    private List<choixReservation> choixReservation;
}
