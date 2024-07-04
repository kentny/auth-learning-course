package org.example.beautybooking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
            AuthenticationSuccessHandler authenticationSuccessHandler
    ) throws Exception {
        httpSecurity
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(authenticationSuccessHandler)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .csrf(csrf -> csrf.disable())
        ;
        return httpSecurity.build();
    }
}
