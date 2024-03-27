package com.joaofreitas.pdvapi.services;

import com.joaofreitas.pdvapi.entities.User;
import com.joaofreitas.pdvapi.exceptions.EntityNotFoundException;
import com.joaofreitas.pdvapi.exceptions.PasswordInvalidException;
import com.joaofreitas.pdvapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
       try {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           return userRepository.save(user);
       } catch (org.springframework.dao.DataIntegrityViolationException ex) {
           throw new RuntimeException("Usuário já cadastrado.");
       }
    }

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
    }

    @Transactional
    public User updatePassword(UUID id, String currentPassword, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordInvalidException("Nova senha não conferem.");
        }

        User user = findById(id);
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

    }

    @Transactional(readOnly = true)
    public User.Role findByRoleUsername(String username) {
        return userRepository.findRoleByUsername(username);
    }
}
