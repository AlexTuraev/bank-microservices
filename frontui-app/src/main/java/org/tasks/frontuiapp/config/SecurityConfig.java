package org.tasks.frontuiapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.tasks.frontuiapp.service.JpaUserDetailsService;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Value("${application.secret.key}")
    private String SECRET_KEY;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, DataSource dataSource) throws Exception {
        return http.authorizeHttpRequests(
                r -> r.requestMatchers("signup", "/actuator/health").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .rememberMe(remember -> remember
                                .key(SECRET_KEY)
                                .tokenRepository(persistentTokenRepository(dataSource))
                                .tokenValiditySeconds(1209600)
                                .rememberMeCookieName("remember-me")
                        )
                .build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();

        repository.setDataSource(dataSource);
//        repository.setCreateTableOnStartup(Boolean.TRUE);

        return repository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserDetailsService();
    }

}
