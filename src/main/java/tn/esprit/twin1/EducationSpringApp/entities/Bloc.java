package tn.esprit.twin1.EducationSpringApp.entities;
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
@Table(name = "Bloc")
public class Bloc {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idBloc;
    @Column(name = "nomBloc",nullable = false)
    private String nomBloc;
    @Column(name = "capaciteBloc",nullable = false)
    private long capaciteBloc;

    @ManyToOne
    Foyer foyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="bloc")
    private Set<Chambre> chambreSet;;

}
