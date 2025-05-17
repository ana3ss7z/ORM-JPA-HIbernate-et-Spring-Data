package com.example.tp2patientsapp.repository;

import com.example.tp2patientsapp.entities.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String n);
    Patient findByNom(String n);
    @Modifying
    @Transactional
    @Query("UPDATE Patient p set p.nom = :nom WHERE p.id = :id")
    void updatePatientNom(@Param("nom") String nom, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Patient p set p.dateNaissance = :date WHERE p.id = :id")
    void updatePatientDate(@Param("date")LocalDate date, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Patient p set p.malade = :malade WHERE p.id = :id")
    void updatePatientMalade(@Param("malade") Boolean malade, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Patient p WHERE p.id=:id")
    int deletePatient(@Param("id") Long id);
}
