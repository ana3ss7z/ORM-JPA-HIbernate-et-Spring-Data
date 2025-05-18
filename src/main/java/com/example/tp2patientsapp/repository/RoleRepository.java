package com.example.tp2patientsapp.repository;

import com.example.tp2patientsapp.entities.Role;
import com.example.tp2patientsapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
