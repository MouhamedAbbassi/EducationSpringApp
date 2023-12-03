package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Etudiant;
import tn.esprit.twin1.EducationSpringApp.entities.Reservation;
import tn.esprit.twin1.EducationSpringApp.entities.TypeChambre;

import java.util.List;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);

    List<Reservation> findAllReservations();

    Reservation findReservationById(long idReservation);

    void Delete(Long id);

    List<Chambre> getNotReservedRooms();

    Reservation updateReservation(Long idReservation, String numeroChambre, TypeChambre typeChambre);
}
