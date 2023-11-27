package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Universite;

import java.util.List;

public interface UniversiteService {
    Universite addUniversite(Universite universite);

    List<Universite> findAllUniversute();

    Universite findUniversiteById(Long id);

    String deleteUniversuteById(Long id);
    Universite updateUniversite(long id, Universite upuniversite);
}
