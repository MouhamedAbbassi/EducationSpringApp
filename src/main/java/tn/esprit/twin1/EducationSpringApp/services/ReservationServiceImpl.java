package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.*;
import tn.esprit.twin1.EducationSpringApp.repositories.ChambreRepositorie;
import tn.esprit.twin1.EducationSpringApp.repositories.ReservationRepositorie;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationRepositorie reservationRepository;

    @Autowired
    private ChambreRepositorie chambreRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {

        Chambre chambre = chambreRepository.findById(reservation.getChambre().getIdChambre())
                .orElseThrow(() -> new IllegalArgumentException("Chambre with idChambre not found"));

        Etudiant etudiant = reservation.getEtudiant();
        if (etudiant != null && etudiant.getReservation() != null) {
            throw new IllegalArgumentException("Etudiant already has a reservation");
        }
        if (reservation.getChoixReservation() != null && !reservation.getChoixReservation().isEmpty()) {
            for (choixReservation choix : reservation.getChoixReservation()) {
                // Validez que le choix est parmi les options autorisées
                if (choix != choixReservation.Piscine && choix != choixReservation.Restauration && choix != choixReservation.Salle_De_Sport) {
                    throw new IllegalArgumentException("Choix de réservation non autorisé : " + choix);
                }
            }
        }
        // Check the number of reservations for each typeChambre
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

        // Add any additional logic if needed before saving the reservation
        reservation.setChambre(chambre);
        reservation.setEtudiant(etudiant);
        return reservationRepository.save(reservation);
    }
    private long countReservationsByType(Chambre chambre,TypeChambre typeChambre) {
        return reservationRepository.countByChambreTypeAndTypeChambre(typeChambre);
    }


    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).isPresent() ? reservationRepository.findById(id).get() : null;
    }

    @Override
    public void Delete(Long idReservation) {

        reservationRepository.deleteById(idReservation);


    }









    @Override
    public Reservation updateReservation(Long idReservation, String numeroChambre, TypeChambre typeChambre) {
        Reservation reservation = reservationRepository.findById(idReservation)
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
            reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("Chambre not found for reservation with id " + idReservation);
        }

        return reservation;
    }

    @Override
    public List<Chambre> getNotReservedRooms() {
        return reservationRepository.findNotReservedRooms();
    }

    @Override
    public List<Etudiant> findEtudiantsWithoutReservation() {
        return reservationRepository.findEtudiantsWithoutReservation();
    }

    @Override
    public Etudiant getEtudiantByIdReservation(Long idReservation) {
        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + idReservation + " not found"));

        return reservation.getEtudiant();
    }
}
