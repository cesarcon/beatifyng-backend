package com.beatifying.backend.auth;

import com.beatifying.backend.auth.filters.JwtAuthenticationFilter;
import com.beatifying.backend.auth.filters.JwtValidationFilter;
import com.beatifying.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/users/destacados").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/users/location").hasRole("USER")
                .requestMatchers("/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/Score").permitAll()
                .requestMatchers(HttpMethod.POST, "/services").hasRole("SELLER")
                .requestMatchers(HttpMethod.PUT, "/services").hasRole("SELLER")
                .requestMatchers(HttpMethod.GET, "/services/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/services/{id}").hasRole("SELLER")
                .requestMatchers(HttpMethod.DELETE, "/services/{id}").hasRole("SELLER")
                .requestMatchers(HttpMethod.GET, "/services/categoria/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/services").permitAll()
                .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/factura").hasRole("BUYER")
                .requestMatchers(HttpMethod.GET, "/factura/compras/{idUsuario}").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/factura/ventas/{idUsuario}").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(),
                        usuarioRepository))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
