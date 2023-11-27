package tn.esprit.twin1.EducationSpringApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.twin1.EducationSpringApp.entities.Universite;

public interface UniversiteRepositorie  extends JpaRepository<Universite,Long> {
}
