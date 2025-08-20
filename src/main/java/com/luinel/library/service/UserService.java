package com.luinel.library.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luinel.library.model.User;
import com.luinel.library.model.form.UpdateUserForm;
import com.luinel.library.model.form.UserForm;
import com.luinel.library.repository.UserRepository;
import com.luinel.library.service.exception.PasswordMatchException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  private boolean matchPassword(String encodedPassword, String rawPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuario não encotrado"));
  }

  private void createUser(UserForm userForm, String role) {
    var user = new User();
    user.setName(userForm.getName());
    user.setPassword(encodePassword(userForm.getPassword()));
    user.setRole(role);
    userRepository.save(user);
  }

  public User findByUser(Long userId) {
    return findUser(userId);
  }

  public String createBaseUser(UserForm userForm) {
    createUser(userForm, "USER");
    return "Usuario criado!";
  }

  public String createAdminUser(UserForm userForm) {
    createUser(userForm, "ADMIN");
    return "Administrador criado!";
  }

  public String updateUser(Long userId, UpdateUserForm userUpdateForm) {
    var user = findUser(userId);
    if (!matchPassword(user.getPassword(), userUpdateForm.getOldPassword())) {
      throw new PasswordMatchException("A palavra-passe não corresponde a antiga");
    }
    if (!userUpdateForm.getNewPassword().equals(userUpdateForm.getConfirmPassword())) {
      throw new PasswordMatchException("As palavras-passe digitadas não coincidem");
    }

    user.setName(userUpdateForm.getName());
    user.setPassword(encodePassword(userUpdateForm.getNewPassword()));

    return "Dados do usuario actualizados!";
  }

  public User getUserById(Long userId) {
    return findUser(userId);
  }

  public String deleteUser(Long userId) {
    userRepository.deleteById(userId);
    return "Usuario removido!";
  }
}
