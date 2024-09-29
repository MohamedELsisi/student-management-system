package com.bank.boubyan.config;


import com.bank.boubyan.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ApplicationConfig applicationConfig;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(ApplicationConfig applicationConfig, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.applicationConfig = applicationConfig;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(applicationConfig)
                .build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeConfig ->
                        authorizeConfig
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/courses").authenticated()
                                .requestMatchers("/api/courses/**").authenticated()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
