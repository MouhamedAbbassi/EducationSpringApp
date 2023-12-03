package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.AddChambreRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import javax.persistence.EntityNotFoundException;

 import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    private final ChambreRepositorie chambreRepositorie;
    private final BlocRepositorie blocRepositorie;

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepositorie.save(chambre);
    }

    @Override
    public List<Chambre> findAllChambre() {
        return chambreRepositorie.findAll();
    }

    @Override
    public Chambre findChambreById(Long id) {
        return chambreRepositorie.findById(id).isPresent() ? chambreRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteChambreById(Long id) {
        if(chambreRepositorie.findById(id).isPresent()){
            chambreRepositorie.deleteById(id);
            return "Deleted"+chambreRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Chambre updateChambre(long id, Chambre upchambre) {
        Chambre chambre = chambreRepositorie.findById(id).orElse(null);

        chambre.setNumeroChambre(upchambre.getNumeroChambre());
        chambre.setTypeChambre(upchambre.getTypeChambre());


        return  chambreRepositorie.save(chambre);
    }

    @Override
    public ResponseEntity<String> addChambreToBloc(AddChambreRequest request) {

        Bloc bloc = blocRepositorie.findByNomBloc(request.getNomBloc());

        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(request.getNumChambre());
        chambre.setTypeChambre(request.getTypeChambre());

        bloc.getChambreSet().add(chambre);
        blocRepositorie.save(bloc);

        return ResponseEntity.ok("Bloc added to Foyer successfully");
    }
}
