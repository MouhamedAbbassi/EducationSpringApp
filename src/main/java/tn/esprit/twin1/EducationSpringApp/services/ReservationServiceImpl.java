package tn.esprit.twin1.EducationSpringApp.services;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.ReservationRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.UserRepository;
import tn.esprit.twin1.EducationSpringApp.services.impl.JWTServiceImpl;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ChambreRepositorie chambreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepositorie reservationRepositorie;

    public Reservation addReservation(Reservation reservation) {

        Chambre chambre = chambreRepository.findById(reservation.getChambre().getIdChambre())
                .orElseThrow(() -> new IllegalArgumentException("Chambre with idChambre not found"));
        int maxSimpleLimit = 1; // Adjust the limit according to your requirements
        int maxDoubleLimit = 2; // Adjust the limit according to your requirements
        int maxTripleLimit = 3; // Adjust the limit according to your requirements

        long countOfSimple = countReservationsByType(chambre, TypeChambre.SIMPLE);
        long countOfDouble = countReservationsByType(chambre, TypeChambre.DOUBLE);
        long countOfTriple = countReservationsByType(chambre, TypeChambre.TRIPLE);
        if (chambre.getTypeChambre() == TypeChambre.SIMPLE && countOfSimple >= maxSimpleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Simple reached for this Chambre");
        }

        if (chambre.getTypeChambre() == TypeChambre.DOUBLE && countOfDouble >= maxDoubleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Double reached for this Chambre");
        }

        if (chambre.getTypeChambre() == TypeChambre.TRIPLE && countOfTriple >= maxTripleLimit) {
            throw new IllegalArgumentException("Maximum limit of typeChambre.Triple reached for this Chambre");
        }

        User user = userRepository.findById(reservation.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User with userId not found"));


        reservation.setChambre(chambre);
        reservation.setUser(user);


       return reservationRepositorie.save(reservation);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepositorie.findAll();
    }

    @Override
    public Reservation findReservationById(long idReservation) {
        return reservationRepositorie.findById(idReservation).isPresent() ? reservationRepositorie.findById(idReservation).get() : null;
    }

    @Override
    public void Delete(Long id) {
        reservationRepositorie.deleteById(id);
    }

    @Override
    public List<Chambre> getNotReservedRooms() {
        return reservationRepositorie.findNotReservedRooms();
    }

    @Override
    public Reservation updateReservation(Long idReservation, String numeroChambre, TypeChambre typeChambre) {
        Reservation reservation = reservationRepositorie.findById(idReservation)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + idReservation + " not found"));

        // Vérifier que le typeChambre est parmi les options autorisées
        if (typeChambre != TypeChambre.SIMPLE && typeChambre != TypeChambre.DOUBLE && typeChambre != TypeChambre.TRIPLE) {
            throw new IllegalArgumentException("Invalid typeChambre: " + typeChambre);
        }

        Chambre chambre = reservation.getChambre();
        if (chambre != null) {
            chambre.setNumeroChambre(numeroChambre);
            chambre.setTypeChambre(typeChambre);

            // Mise à jour de la réservation
           reservationRepositorie.save(reservation);
        } else {
            throw new IllegalArgumentException("Chambre not found for reservation with id " + idReservation);
        }

        return reservation;
    }

    private long countReservationsByType(Chambre chambre,TypeChambre typeChambre) {
        return reservationRepositorie.countByChambreTypeAndTypeChambre(typeChambre);
    }

}
