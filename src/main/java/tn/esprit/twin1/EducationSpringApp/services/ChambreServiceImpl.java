package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements ChambreService {

    private final ChambreRepositorie chambreRepositorie;

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
}
