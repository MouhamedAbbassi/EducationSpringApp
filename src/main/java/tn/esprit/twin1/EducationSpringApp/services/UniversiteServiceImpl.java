package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Universite;
import tn.esprit.twin1.EducationSpringApp.repositories.UniversiteRepositorie;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements UniversiteService{

    private final UniversiteRepositorie universiteRepositorie;
    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepositorie.save(universite);
    }

    @Override
    public List<Universite> findAllUniversute() {
        return universiteRepositorie.findAll();
    }

    @Override
    public Universite findUniversiteById(Long id) {
        return universiteRepositorie.findById(id).orElse(null);
    }

    @Override
    public String deleteUniversuteById(Long id) {
        if(universiteRepositorie.findById(id).isPresent()){
            universiteRepositorie.deleteById(id);
            return "Deleted"+universiteRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";
    }

    @Override
    public Universite updateUniversite(long id, Universite upuniversite) {
        Universite universite = universiteRepositorie.findById(id).orElse(null);

        universite.setNomUniversite(upuniversite.getNomUniversite());
        universite.setAdresse(upuniversite.getAdresse());

        return  universiteRepositorie.save(universite);
    }
}
