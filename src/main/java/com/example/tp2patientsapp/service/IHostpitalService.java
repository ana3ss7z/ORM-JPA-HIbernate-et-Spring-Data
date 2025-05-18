package com.example.tp2patientsapp.service;

import com.example.tp2patientsapp.entities.Consultation;
import com.example.tp2patientsapp.entities.Medecin;
import com.example.tp2patientsapp.entities.Patient;
import com.example.tp2patientsapp.entities.RendezVous;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IHostpitalService {
    Patient savePatient(Patient patient);
    void updatePatientNom(String nom,Long id);
    void updatePatientDate(LocalDate date,Long id);
    void updatePatientMalade(Boolean malade, Long id);
    int deletePatient(Long id);


    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
