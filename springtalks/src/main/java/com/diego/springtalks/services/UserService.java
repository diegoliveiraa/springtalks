package com.diego.springtalks.services;

import com.diego.springtalks.dtos.requests.UserDTO;
import com.diego.springtalks.entities.User;
import com.diego.springtalks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(UserDTO userDTO) {

        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public List<User> findAllById(List<UUID> ids) {
        System.out.println("Ids da Requisicao: " + ids);
        return this.userRepository.findAllById(ids);
    }

    public Optional<User> findById(UUID id) {

        return this.userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
