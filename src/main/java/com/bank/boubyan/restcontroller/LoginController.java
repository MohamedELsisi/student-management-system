package com.bank.boubyan.restcontroller;


import com.bank.boubyan.dto.LoginRequest;
import com.bank.boubyan.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Login Management")
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

    @Operation(summary = "User Login", description = "Authenticates a user and generates a JWT token.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid credentials."),
            @ApiResponse(responseCode = "400", description = "Bad Request, validation error.")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        return ResponseEntity.ok(jwtService.generateJwt(userDetails.loadUserByUsername(loginRequest.getUsername())));
    }

}
