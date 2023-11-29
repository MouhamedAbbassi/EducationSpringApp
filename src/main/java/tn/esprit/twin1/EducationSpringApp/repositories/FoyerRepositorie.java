package tn.esprit.twin1.EducationSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;

import java.util.Optional;

public interface FoyerRepositorie  extends JpaRepository<Foyer,Long> {
    Optional<Foyer> findByNomFoyer(String nomFoyer);

}
