package br.com.tapera.sustentabilidade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa proteção CSRF
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite frames (necessário para o Swagger e H2)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // LIBERA TUDO para facilitar seu desenvolvimento
                )
                .formLogin(form -> form.disable()); // Desativa a página de login automática

        return http.build();
    }
}