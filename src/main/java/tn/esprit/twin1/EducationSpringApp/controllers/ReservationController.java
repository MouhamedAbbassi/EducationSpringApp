package tn.esprit.twin1.EducationSpringApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Etudiant;
import tn.esprit.twin1.EducationSpringApp.entities.Reservation;
import tn.esprit.twin1.EducationSpringApp.services.ChambreServiceImpl;
import tn.esprit.twin1.EducationSpringApp.services.ReservationService;
import tn.esprit.twin1.EducationSpringApp.services.ReservationServiceImpl;

import java.util.List;

@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        try {
            Reservation addedReservation = reservationService.addReservation(reservation);
            return new ResponseEntity<>(addedReservation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> reservations = reservationService.findAllReservations();
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getBy/{idReservation}")
    public Reservation getId(@PathVariable long idReservation) {
        return reservationService.findReservationById(idReservation);
    }

    @DeleteMapping("/delete/{idReservation}")
    public ResponseEntity<?> Delete(@PathVariable("idReservation") Long id) {
        reservationService.Delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/not-reserved")
    public List<Chambre> getNotReservedRooms() {
        return reservationService.getNotReservedRooms();
    }
    @PutMapping("/update/{idReservation}")
    public ResponseEntity<?> updateChambreInfo(@PathVariable Long idReservation, @RequestBody Chambre chambreUpdateRequest) {
        try {
            reservationService.updateReservation(idReservation, chambreUpdateRequest.getNumeroChambre(), chambreUpdateRequest.getTypeChambre());
            return new ResponseEntity<>("Chambre information updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

