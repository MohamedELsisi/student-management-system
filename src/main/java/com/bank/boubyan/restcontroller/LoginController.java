package com.bank.boubyan.restcontroller;


import com.bank.boubyan.dto.LoginRequest;
import com.bank.boubyan.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetails;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginController(JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetails) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetails = userDetails;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        return ResponseEntity.ok(jwtService.generateJwt(userDetails.loadUserByUsername(loginRequest.getUsername())));
    }

}
