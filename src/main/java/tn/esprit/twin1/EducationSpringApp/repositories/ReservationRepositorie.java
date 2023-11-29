package tn.esprit.twin1.EducationSpringApp.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.twin1.EducationSpringApp.entities.Chambre;
import tn.esprit.twin1.EducationSpringApp.entities.Etudiant;
import tn.esprit.twin1.EducationSpringApp.entities.Reservation;
import tn.esprit.twin1.EducationSpringApp.entities.TypeChambre;

import java.util.List;

@Repository
public interface ReservationRepositorie  extends JpaRepository<Reservation,Long> {

    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.chambre c WHERE c.typeChambre = :typeChambre")
    long countByChambreTypeAndTypeChambre(@Param("typeChambre") TypeChambre typeChambre);

    @Query("SELECT c FROM Chambre c " +
            "WHERE (c.idChambre NOT IN (SELECT r.chambre.idChambre FROM Reservation r) " +
            "OR (c.typeChambre = 'Double' AND (SELECT COUNT(r) FROM Reservation r WHERE r.chambre.idChambre = c.idChambre) < 2) " +
            "OR (c.typeChambre = 'Triple' AND (SELECT COUNT(r) FROM Reservation r WHERE r.chambre.idChambre = c.idChambre) < 3))")
    List<Chambre> findNotReservedRooms();

    @Query("SELECT e FROM Etudiant e WHERE NOT EXISTS (SELECT 1 FROM Reservation r WHERE r.etudiant = e)")
    List<Etudiant> findEtudiantsWithoutReservation();



    Etudiant getEtudiantByIdReservation(Integer reservationId);

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.chambre.numeroChambre = :numeroChambre, r.chambre.typeChambre = :typeChambre WHERE r.idReservation = :idReservation")
    void updateChambreInfo(@Param("idReservation") Integer idReservation, @Param("numeroChambre") Integer numChambre, @Param("typeChambre") TypeChambre typeChambre);
}
