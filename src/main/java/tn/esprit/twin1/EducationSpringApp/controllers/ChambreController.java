package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.BlocDto;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.ChambreDto;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import tn.esprit.twin1.EducationSpringApp.services.ChambreService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/chambre")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChambreController {

    private final ChambreService chambreService;
    private final ChambreRepositorie chambreRepositorie;
    @GetMapping("/chambres")
     public List<ChambreDto> getAllBlocs() {
        List<Chambre> chambres = chambreRepositorie.findAll();
        List<ChambreDto> chambreDtos = new ArrayList<>();

        for (Chambre chambre : chambres) {
            ChambreDto chambreDto = new ChambreDto();
            chambreDto.setIdChambre(chambre.getIdChambre());
            chambreDto.setNumChambre(chambre.getNumeroChambre());
            chambreDto.setTypeChambre(chambre.getTypeChambre());
            chambreDto.setNomBloc(chambre.getBloc().getNomBloc()); // Assuming Foyer has a 'name' property


            chambreDtos.add(chambreDto);
        }

        return chambreDtos;
    }

    @PostMapping("/new")
    public Chambre addFoyer(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/update/{idChambre}")
    public Chambre updateChambre(@PathVariable long idChambre,@RequestBody Chambre chambre) {
        return chambreService.updateChambre(idChambre,chambre);
    }

    @GetMapping("/getId/{idChambre}")
    public Chambre getId(@PathVariable long idChambre) {
        return chambreService.findChambreById(idChambre);
    }

    @DeleteMapping("/delete/{idChambre}")
    public ResponseEntity<String> deletechambre(@PathVariable long idChambre) {
        try {
            Chambre chambre = chambreService.findChambreById(idChambre);
            if ( chambre != null) {
                chambreService.deleteChambreById(idChambre);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
