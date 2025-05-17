package com.example.tp2patientsapp;

import com.example.tp2patientsapp.entities.Medecin;
import com.example.tp2patientsapp.entities.Patient;
import com.example.tp2patientsapp.entities.RendezVous;
import com.example.tp2patientsapp.entities.StatusRDV;
import com.example.tp2patientsapp.repository.MedecinRepository;
import com.example.tp2patientsapp.repository.PatientRepository;
import com.example.tp2patientsapp.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp2PatientsAppApplication  {
//    @Autowired
//    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp2PatientsAppApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//// ---------------Ajouter des patients------------------------
//    patientRepository.save(new Patient(null,"Anass", LocalDate.of(2002, 4, 20), false,12));
//    patientRepository.save(new Patient(null,"Jack", LocalDate.of(2001, 2, 6), false,17));
//    patientRepository.save(new Patient(null,"youssouf", LocalDate.of(1999, 11, 24), true,22));
//
////    ------------------- Consulter tous les patients-----------------
//        List<Patient> AllPatients = patientRepository.findAll();
//        AllPatients.forEach(p ->{
//            System.out.println("-----patient-------------");
//            System.out.println(p.getId());
//            System.out.println(p.getNom());
//            System.out.println(p.getDateNaissance());
//            System.out.println(p.getScore());
//        });
////-------------------- Consulter un Patient ------------
//        Patient p = patientRepository.findById(1L).get();
//        System.out.println("-------------Consultation du Pateint-------");
//        System.out.println("Patient Id: " + p.getId());
//        System.out.println("Patient Nom: " + p.getNom());
//        System.out.println("Patient Date Naissance: " + p.getDateNaissance());
//        System.out.println("Patient Score: " + p.getScore());
//
////    ------------------Chercher un patient----------------
//        List<Patient> CherchP = patientRepository.findByNomContains("a");
//        CherchP.forEach(pc ->{
//            System.out.println("-----patients trouvés-------------");
//            System.out.println(pc.getId());
//            System.out.println(pc.getNom());
//            System.out.println(pc.getDateNaissance());
//            System.out.println(pc.getScore());
//        });
////     -----------------Modifer le nom du patient---------------------
//        patientRepository.updatePatientNom("Ahmed",2L);
//        Patient pUpdate = patientRepository.findById(2L).get();
//        System.out.println("-------------Patient apres la modéfication-------");
//        System.out.println("Patient Id: " + pUpdate.getId());
//        System.out.println("Patient Nom: " + pUpdate.getNom());
//        System.out.println("Patient Date Naissance: " + pUpdate.getDateNaissance());
//        System.out.println("Patient Score: " + pUpdate.getScore());
//
////     -----------------Modifer la Date de naissance du patient---------------------
//        patientRepository.updatePatientDate(LocalDate.of(2000,12,03),2L);
//
////     -----------------Modifer l'etat' du patient---------------------
//        patientRepository.updatePatientMalade(false,3L);
//        patientRepository.updatePatientMalade(true,2L);
//// ---------------- Supprimer Un patient-------------------
//        patientRepository.deletePatient(3L);
//    }


    @Bean
    CommandLineRunner start(PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            MedecinRepository medecinRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "anass",new Date() ,false,null));

            Stream.of("dr,Hassan","dr.Salah","dr.Karim ")
                    .forEach(name -> {
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.4?"Cardio":"Dentiste");
                        medecinRepository.save(medecin);
                    });
        Patient p1 = patientRepository.findById(1L).orElse(null);
        Patient p2 = patientRepository.findByNom("anass");

        Medecin m1 = medecinRepository.findByNomContains("Karim");

        RendezVous r1 = new RendezVous();
        r1.setDate(new Date());
        r1.setStatus(StatusRDV.CANCELED);
        r1.setMedecin(m1);
        r1.setPatient(p1);
        rendezVousRepository.save(r1);
        };



    }
}
