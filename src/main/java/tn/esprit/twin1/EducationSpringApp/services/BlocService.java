package tn.esprit.twin1.EducationSpringApp.services;

import org.springframework.http.ResponseEntity;
import tn.esprit.twin1.EducationSpringApp.entities.AddBlocRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;

import java.util.List;

public interface BlocService {
    Bloc addBloc(Bloc bloc  );
   // Bloc addBlocToFoyer(long idBloc , Foyer foyer);

    List<Bloc> findAllBloc();

    Bloc findBlocById(Long id);

    String deleteBlocById(Long id);
    Bloc updateBloc(long id, Bloc upbloc);

    // Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc);
    ResponseEntity<String> addBlocToFoyer(AddBlocRequest request);
}
