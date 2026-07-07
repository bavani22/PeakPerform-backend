package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUser.Role;
import com.example.demo.service.AuthService;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(
            AuthService service,
            JwtService jwtService,
            CustomUserDetailsService userDetailsService) {

        this.service = service;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public AppUser register(@Valid @RequestBody AppUser user){
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AppUser user){

    AppUser dbUser =service.login(user);

    return jwtService.generateToken(userDetailsService.loadUserByUsername(dbUser.getEmail()));
    }

    @GetMapping("/users")
    public List<AppUser> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/users/by-role")
    public List<AppUser> getUsersByRole(@RequestParam Role role){

        return service.getUsersByRole(role);
    }

   
}