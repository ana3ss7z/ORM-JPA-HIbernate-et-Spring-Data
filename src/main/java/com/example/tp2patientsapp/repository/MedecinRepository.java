package com.example.tp2patientsapp.repository;

import com.example.tp2patientsapp.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Medecin findByNomContains(String nom);
}
