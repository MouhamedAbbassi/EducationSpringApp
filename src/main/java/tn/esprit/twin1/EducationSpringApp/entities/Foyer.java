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
@ToString(includeFieldNames = false)
@Table(name = "Foyer")
public class Foyer {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long idFoyer;
    @Column(name = "nomFoyer",nullable = false)
    private String nomFoyer;
    @Column(name = "capaciteFoyer",nullable = false)
    private long capaciteFoyer;

    @OneToOne
    private Universite universite;


    @OneToMany(cascade = CascadeType.ALL, mappedBy="foyer")
    private Set<Bloc> blocSet;
}
