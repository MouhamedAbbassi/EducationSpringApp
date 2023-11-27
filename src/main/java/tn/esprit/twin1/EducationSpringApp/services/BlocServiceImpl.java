package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.FoyerRepositorie;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements BlocService{

    private final BlocRepositorie blocRepositorie;
    private final FoyerRepositorie foyerRepositorie;

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepositorie.save(bloc);
    }

    @Override
    public List<Bloc> findAllBloc() {
        return blocRepositorie.findAll();
    }

    @Override
    public Bloc findBlocById(Long id) {
        return blocRepositorie.findById(id).isPresent() ? blocRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteBlocById(Long id) {
        if(blocRepositorie.findById(id).isPresent()){
            blocRepositorie.deleteById(id);
            return "Deleted"+blocRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Bloc updateBloc(long id, Bloc upbloc) {
        Bloc bloc = blocRepositorie.findById(id).orElse(null);

        bloc.setNomBloc(upbloc.getNomBloc());
        bloc.setCapaciteBloc(upbloc.getCapaciteBloc());


        return  blocRepositorie.save(bloc);
    }

    @Override
    public Bloc addBlocAndAsigneToFoyer(long idFoyer, Bloc bloc) {
        Foyer foyer = foyerRepositorie.findById(idFoyer).orElse(null);
        bloc.setFoyer(foyer);
        return blocRepositorie.save(bloc);
    }
}
