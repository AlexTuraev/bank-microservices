package org.tasks.frontuiapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                r -> r.requestMatchers("signup").permitAll()
                        .anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }

}
