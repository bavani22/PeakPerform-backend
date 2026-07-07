package com.example.demo.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUser.Role;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppUserRepository;

@Service
public class AuthService {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(AppUserRepository repo, PasswordEncoder encoder,AuthenticationManager authenticationManager) {
        this.repo = repo;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    public AppUser register(AppUser user){

        if(repo.existsByEmail(user.getEmail())){
            throw new BusinessValidationException("Email already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public AppUser login(AppUser user){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( user.getEmail(),user.getPassword()));

        return repo.findByEmail(user.getEmail()).orElseThrow(() ->
        new ResourceNotFoundException("User not found"));
    }

    public List<AppUser> getAllUsers(){
        return repo.findByIsActiveTrue();
    }

    public List<AppUser> getUsersByRole(Role role){
        return repo.findByRole(role);
    }
}