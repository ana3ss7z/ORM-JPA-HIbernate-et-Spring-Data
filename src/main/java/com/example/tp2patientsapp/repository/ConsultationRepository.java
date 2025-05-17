package com.example.tp2patientsapp.repository;

import com.example.tp2patientsapp.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
