package com.example.tp2patientsapp.service;

import com.example.tp2patientsapp.entities.Role;
import com.example.tp2patientsapp.entities.User;

public interface IUserService {
    User addNewuser(User user);
    Role addNewrole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRolename(String rolename);
    void addRoleToUser(String username, String rolename);
    User authenticate(String username, String password);
}
