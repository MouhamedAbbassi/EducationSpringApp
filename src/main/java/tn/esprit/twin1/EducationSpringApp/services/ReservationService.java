package tn.esprit.twin1.EducationSpringApp.services;

import tn.esprit.twin1.EducationSpringApp.entities.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);

    List<Reservation> findAllReservation();

    Reservation findReservationById(Long id);

    String deleteReservationById(Long id);
    Reservation updateReservation(long id, Reservation updatedR);
}
