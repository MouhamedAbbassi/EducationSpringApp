package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.services.ChambreService;

@RequestMapping("/chambre")
@RequiredArgsConstructor
@RestController
public class ChambreController {

    private final ChambreService chambreService;

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
