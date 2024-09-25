package com.example.auth_form.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_form.model.AuthRequest;
import com.example.auth_form.model.UsersModel;
import com.example.auth_form.service.UsersService;

@RestController
@RequestMapping("/api")
public class UsersController {
  @Autowired
  private UsersService usersService;

  @CrossOrigin(origins = "https://react-form-auth.vercel.app")
  @PostMapping("/signup")
  public ResponseEntity<?> register(@RequestBody AuthRequest request) {
    try {
      UsersModel user = usersService.registerUser(request.getEmail(), request.getPassword());
      return ResponseEntity.ok(Collections.singletonMap("message", "Inscription réussie :" + user.getEmail()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
    }
  }

  @CrossOrigin(origins = "https://react-form-auth.vercel.app")
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest request) {
      try {
        UsersModel user = usersService.authenticateUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(Collections.singletonMap("message", "Connexion réussie :" + user.getEmail()));
      } catch(Exception e) {
        return ResponseEntity.status(401).body(Collections.singletonMap("error", e.getMessage()));

      }
  }
  
}
