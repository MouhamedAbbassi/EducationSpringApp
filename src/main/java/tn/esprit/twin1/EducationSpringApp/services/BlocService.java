package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Bloc;

import java.util.List;

public interface BlocService {
    Bloc addBloc(Bloc bloc);

    List<Bloc> findAllBloc();

    Bloc findBlocById(Long id);

    String deleteBlocById(Long id);
    Bloc updateBloc(long id, Bloc upbloc);

    Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc);
}
