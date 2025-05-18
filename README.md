
# TP N°2 - ORM JPA HIbernate et Spring Data

Ce projet est une application Spring Boot permettant la gestion de patients, médecins, rendez-vous, consultations, utilisateurs et rôles. Il s'appuie sur Spring Data JPA pour la persistance des données, H2/MySQL comme bases de données, et utilise Lombok pour réduire le code boilerplate.

## 🛠 Technologies utilisées

- Java 17+
- Spring Boot
  - Spring Web
  - Spring Data JPA
- H2 Database (puis migration vers MySQL)
- MySQL
- Lombok
- Maven

---

## 📁 Structure du projet

```

\---target
+---classes
\|   |   application.properties
\|   |
\|   ---com
\|       ---example
\|           ---tp2patientsapp
\|               |   Tp2PatientsAppApplication.class
\|               |
\|               +---entities
\|               |       Patient.class
\|               |       Medecin.class
\|               |       RendezVous.class
\|               |       Consultation.class
\|               |       User.class
\|               |       Role.class
\|               |       StatusRDV.class
\|               |
\|               +---repository
\|               |       PatientRepository.class
\|               |       MedecinRepository.class
\|               |       RendezVousRepository.class
\|               |       ConsultationRepository.class
\|               |       UserRepository.class
\|               |       RoleRepository.class
\|               |
\|               ---service
\|                       IHospitalService.class
\|                       HospitalServiceImpl.class
\|                       IUserService.class
\|                       UserServiceImpl.class

````

---

## ✅ Étapes Réalisées

### 1. Création du projet Spring Initializer

Le projet a été généré avec les dépendances suivantes :
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok

### 2. Entité `Patient`

L'entité `Patient` est une entité JPA avec les attributs suivants :

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
````

### 3. Configuration H2 (`application.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

### 4. Repository JPA

```java
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String keyword);
}
```

### 5. Opérations de gestion de patients

Les opérations suivantes ont été testées via un `CommandLineRunner` :

* **Ajout de patients**
* **Affichage de tous les patients**
* **Consultation d’un patient par ID**
* **Recherche de patients par nom**
* **Mise à jour des données**
* **Suppression d’un patient**

### 6. Migration vers MySQL

Remplacement de H2 par MySQL dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### 7. Extensions (Patients, Médecins, RDV, Consultations, Utilisateurs & Rôles)

Les entités supplémentaires suivantes ont été ajoutées et liées :

* **Medecin** : avec spécialité et email
* **RendezVous** : lié à Patient et Médecin
* **Consultation** : lié à un RendezVous
* **User** et **Role** : pour la gestion de la sécurité

### Exemple : entité Médecin

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String specialite;
}
```

---

## 📌 Lancement de l’application

* Vérifier que la base de données (H2 ou MySQL) est accessible.
* Lancer l'application via la méthode `main()` dans `Tp2PatientsAppApplication`.
* Accéder à la console H2 via `http://localhost:8080/h2-console` (si H2 activée).

---

## 🔁 À faire

* Ajouter une interface REST pour exposer les opérations.
* Sécuriser l’application avec Spring Security.
* Ajouter des tests unitaires et d’intégration.

---

## ✍️ Auteur

Travail réalisé dans le cadre du TP2 - Programmation Java Spring Boot.

```

---

Souhaitez-vous que je vous génère aussi un exemple de code pour chaque entité (Médecin, RendezVous, etc.) ou les interfaces de service ?
```
