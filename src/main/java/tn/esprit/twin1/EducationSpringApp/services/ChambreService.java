package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Chambre;

import java.util.List;

public interface ChambreService {
    Chambre addChambre(Chambre  chambre);

    List<Chambre> findAllChambre();

    Chambre findChambreById(Long id);

    String deleteChambreById(Long id);
    Chambre updateChambre(long id, Chambre upchambre);
}
