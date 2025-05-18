package com.example.tp2patientsapp.service;

import com.example.tp2patientsapp.entities.Consultation;
import com.example.tp2patientsapp.entities.Medecin;
import com.example.tp2patientsapp.entities.Patient;
import com.example.tp2patientsapp.entities.RendezVous;

public interface IHostpitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
