package tn.esprit.twin1.EducationSpringApp.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.AddBlocRequest;
import tn.esprit.twin1.EducationSpringApp.entities.Bloc;
import tn.esprit.twin1.EducationSpringApp.entities.Foyer;
import tn.esprit.twin1.EducationSpringApp.repositories.BlocRepositorie;
import tn.esprit.twin1.EducationSpringApp.services.BlocService;

import java.util.List;

@Slf4j
@RequestMapping("/bloc")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class BlocCotroller {
    private final BlocService blocService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Bloc>> getAllEtudiant() {
        List<Bloc> etudiants = blocService.findAllBloc();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @PostMapping("/new")
    public Bloc addBloc(@RequestBody Bloc bloc) {

        return blocService.addBloc(bloc);
    }

    @PutMapping("/update/{idBloc}")
    public Bloc updateFoyer(@PathVariable long idBloc,@RequestBody Bloc bloc) {
        return blocService.updateBloc(idBloc,bloc);
    }

    @GetMapping("/getId/{idBloc}")
    public Bloc getId(@PathVariable long idBloc) {
        return blocService.findBlocById(idBloc);
    }

    @DeleteMapping("/delete/{idBloc}")
    public ResponseEntity<String> deleteFoyer(@PathVariable long idBloc) {
        try {
            Bloc bloc = blocService.findBlocById(idBloc);
            if ( bloc != null) {
                blocService.deleteBlocById(idBloc);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions with a 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/foyers/addBloc")
    public ResponseEntity<String> addBlocToFoyer(@RequestBody AddBlocRequest request) {

        System.out.println("aaaaaaaaa"+request);
       blocService.addBlocToFoyer(request);
        return ResponseEntity.ok("Bloc added to Foyer successfully");
    }

}
