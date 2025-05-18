package com.example.tp2patientsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class RendezVous {
    @Id
    private String id;
//    private String nom;
    private Date date;
    @Enumerated(EnumType.STRING)
    private StatusRDV status;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecin medecin;
    @OneToOne (mappedBy = "rendezVous")
    private Consultation consultation;



}
