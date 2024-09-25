package com.example.auth_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_form.model.UsersModel;
import com.example.auth_form.repository.UsersRepository;

@Service
public class UsersService {
  @Autowired
  private UsersRepository usersRepository;

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UsersModel registerUser(String email, String password) throws Exception {
    if (usersRepository.findByEmail(email).isPresent()) {
      throw new Exception("L'email est déjà utilisé");
    }

    UsersModel user = new UsersModel();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password));
    return usersRepository.save(user);
  }

  public UsersModel authenticateUser(String email, String password) throws Exception {
    UsersModel user = usersRepository.findByEmail(email).orElseThrow(() -> new Exception("Utilisateur non trouvé"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new Exception("Mot de passe incorect");
    }

    return user;
  }
}
