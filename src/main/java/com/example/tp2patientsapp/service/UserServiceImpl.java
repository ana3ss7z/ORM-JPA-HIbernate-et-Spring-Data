package com.example.tp2patientsapp.service;

import com.example.tp2patientsapp.entities.Role;
import com.example.tp2patientsapp.entities.User;
import com.example.tp2patientsapp.repository.RoleRepository;
import com.example.tp2patientsapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements  IUserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

//    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
//        this.roleRepository = roleRepository;
//        this.userRepository = userRepository;
//    }

    @Override
    public User addNewuser(User user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewrole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRolename(String rolename) {
        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = findUserByUsername(username);
        Role role = findRoleByRolename(rolename);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public User authenticate(String username, String password) {
        User user = findUserByUsername(username);

        if (user == null) throw new RuntimeException("Invalid username or password");
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
