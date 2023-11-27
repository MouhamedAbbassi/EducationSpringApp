package tn.esprit.twin1.EducationSpringApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.twin1.EducationSpringApp.entities.Reservation;
import tn.esprit.twin1.EducationSpringApp.repositories.ReservationRepositorie;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepositorie reservationRepositorie;

    @Override
    public Reservation addReservation(Reservation reservation) {
       return reservationRepositorie.save(reservation);
    }

    @Override
    public List<Reservation> findAllReservation() {
        return (List<Reservation>)reservationRepositorie.findAll();
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepositorie.findById(id).isPresent() ? reservationRepositorie.findById(id).get() : null;
    }

    @Override
    public String deleteReservationById(Long id) {
        if(reservationRepositorie.findById(id).isPresent()){
            reservationRepositorie.deleteById(id);
            return "Deleted"+reservationRepositorie.findById(id).get().toString();
        }else
            return "etudiant with ID : "+id+" Doesn't exist";    }

    @Override
    public Reservation updateReservation(long id, Reservation updatedR) {
        Reservation reservation = reservationRepositorie.findById(id).orElse(null);

        reservation.setAnneUniversitaire(updatedR.getAnneUniversitaire());

        return  reservationRepositorie.save(reservation);
    }
}
