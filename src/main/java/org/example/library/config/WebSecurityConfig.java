package org.example.library.config;

import lombok.AllArgsConstructor;
import org.example.library.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF защиту для упрощения работы с Postman
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/api/accounts/**").permitAll()
                        .requestMatchers("/api/orders/**", "/api/journals/**").authenticated()
                        .requestMatchers("/api/orders/deactivate/**", "/api/journals/create").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(); // Используем базовую авторизацию

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF защиту для упрощения работы с Postman
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/api/accounts/**", "/api/orders/**", "/api/journals/**").permitAll() // Разрешаем доступ к регистрации/логину
                        //.requestMatchers("/api/orders/deactivate/**", "/api/journals/create").hasRole("ADMIN") // Только для ADMIN
                        .anyRequest().authenticated() // Все остальные запросы требуют авторизации
                )
                .httpBasic(); // Используем базовую авторизацию (или заменяем на JWT, если используется)

        return http.build();
    }*/


}
