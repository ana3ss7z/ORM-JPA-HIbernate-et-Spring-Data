package com.example.tp2patientsapp.repository;

import com.example.tp2patientsapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
