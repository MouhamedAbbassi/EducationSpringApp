package tn.esprit.twin1.EducationSpringApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@Table(name = "Universite")
public class Universite {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private long idUniversite;

    @Column(name = "nomUniversite",nullable = false)
    private String nomUniversite;

    @Column(name = "adresse",nullable = false)
    private String adresse;

    @JsonIgnore
    @OneToOne(mappedBy = "universite", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Foyer foyer;



}
