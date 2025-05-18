package com.example.tp2patientsapp;

import com.example.tp2patientsapp.entities.*;
import com.example.tp2patientsapp.repository.MedecinRepository;
import com.example.tp2patientsapp.repository.PatientRepository;
import com.example.tp2patientsapp.repository.RendezVousRepository;
import com.example.tp2patientsapp.repository.RoleRepository;
import com.example.tp2patientsapp.service.IHostpitalService;
import com.example.tp2patientsapp.service.IUserService;
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

    @Bean
    CommandLineRunner start(IHostpitalService hostpitalService,
                            IUserService userService,
                            PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            MedecinRepository medecinRepository, RoleRepository roleRepository) {
        return args -> {
            //// ---------------Ajouter des patients------------------------
            hostpitalService.savePatient(new Patient(null,"Anass", new Date(), false,null));
            hostpitalService.savePatient(new Patient(null,"Jack", new Date(), false,null));
            hostpitalService.savePatient(new Patient(null,"youssouf", new Date(), true,null));


            ////     -----------------Modifer le nom du patient---------------------
        hostpitalService.updatePatientNom("Ahmed",2L);
        Patient pUpdate = patientRepository.findById(2L).get();
        System.out.println("-------------Patient apres la modéfication-------");
        System.out.println("Patient Id: " + pUpdate.getId());
        System.out.println("Patient Nom: " + pUpdate.getNom());
        System.out.println("Patient Date Naissance: " + pUpdate.getDateNaissance());

            ////     -----------------Modifer la Date de naissance du patient---------------------
        hostpitalService.updatePatientDate(LocalDate.of(2000,12,03),2L);

            ////     -----------------Modifer l'etat' du patient---------------------
        hostpitalService.updatePatientMalade(false,3L);
        hostpitalService.updatePatientMalade(true,2L);


            //// ---------------- Supprimer Un patient-------------------
        hostpitalService.deletePatient(3L);

            //// ---------------Ajouter des Medecins------------------------

            Stream.of("dr,Hassan","dr.Salah","dr.Karim ")
                    .forEach(name -> {
                        Medecin medecin = new Medecin();
                        medecin.setNom(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpecialite(Math.random()>0.4?"Cardio":"Dentiste");
                        hostpitalService.saveMedecin(medecin);
                    });
        Patient p1 = patientRepository.findById(1L).orElse(null);
        Patient p2 = patientRepository.findByNom("anass");

        Medecin m1 = medecinRepository.findByNomContains("Karim");
// -------------Ajouter des rendezèvous-----------------------
        RendezVous r1 = new RendezVous();
        r1.setDate(new Date());
        r1.setStatus(StatusRDV.CANCELED);
        r1.setMedecin(m1);
        r1.setPatient(p1);
        hostpitalService.saveRendezVous(r1);

// -----------------Ajouter des Consultations-------------------------

        RendezVous r2 = rendezVousRepository.findAll().get(0);
        Consultation c1 = new Consultation();
        c1.setDateConsultation(new Date());
        c1.setRendezVous(r2);
        c1.setRapport("Rapport du consultation 1");
        hostpitalService.saveConsultation(c1);

//    -------------------User and Role Part---------------
        User u1 = new User();
        u1.setUsername("anass");
        u1.setPassword("1234");
        userService.addNewuser(u1);

        User u2 = new User();
        u2.setUsername("aymane");
        u2.setPassword("12345");
        userService.addNewuser(u2);


        Stream.of("ADMIN","USER","DEVELOPER")
                .forEach(r->{
                    Role role = new Role();
                    role.setRoleName(r);
                    userService.addNewrole(role);
                });
//        User uu = userService.findUserByUsername("anass");
//            System.out.println(uu.getUsername());
        userService.addRoleToUser("anass","USER");
        userService.addRoleToUser("anass","DEVELOPER");
        userService.addRoleToUser("aymane","USER");
        userService.addRoleToUser("aymane","ADMIN");

//        ---------------Authentification------------
        try {

            User conUser = userService.authenticate("anass","1234");
            System.out.println("Connection reussite : ");
            System.out.println(conUser.getId());
            System.out.println(conUser.getUsername());
            System.out.println("Roles : ");
            conUser.getRoles().forEach(role->{
//                System.out.println("Role : "+role.getRoleName());
                System.out.println("Role : "+role);
            });


        }catch (Exception e){
            e.printStackTrace();
        }

        };



    }
}
