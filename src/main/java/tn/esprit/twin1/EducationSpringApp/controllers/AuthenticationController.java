package tn.esprit.twin1.EducationSpringApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.twin1.EducationSpringApp.dto.JwtAuthenticationResponse;
import tn.esprit.twin1.EducationSpringApp.dto.LoginRequest;
import tn.esprit.twin1.EducationSpringApp.dto.SignUpRequest;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.services.AuthenticationService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")


public class AuthenticationController {

    
    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User>sighup(@RequestBody SignUpRequest signUpRequest)
    {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));

    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));

    }



}
