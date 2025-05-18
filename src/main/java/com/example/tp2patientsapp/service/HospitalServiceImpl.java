package com.example.tp2patientsapp.service;

import com.example.tp2patientsapp.entities.Consultation;
import com.example.tp2patientsapp.entities.Medecin;
import com.example.tp2patientsapp.entities.Patient;
import com.example.tp2patientsapp.entities.RendezVous;
import com.example.tp2patientsapp.repository.ConsultationRepository;
import com.example.tp2patientsapp.repository.MedecinRepository;
import com.example.tp2patientsapp.repository.PatientRepository;
import com.example.tp2patientsapp.repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class HospitalServiceImpl implements  IHostpitalService{
    private final RendezVousRepository rendezVousRepository;
    private final ConsultationRepository consultationRepository;
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVous rendezVous;
    private Consultation consultation;

    public HospitalServiceImpl(PatientRepository patientRepository,
                               MedecinRepository medecinRepository,
                               RendezVous rendezVous,
                               Consultation consultation, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVous = rendezVous;
        this.consultation = consultation;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
