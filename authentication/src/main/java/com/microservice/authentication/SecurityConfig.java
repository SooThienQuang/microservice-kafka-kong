package com.microservice.authentication;
import com.microservice.authentication.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // ✅ Cách mới để disable CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // ✅ Cấu hình rule cho request
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/authentication/login").permitAll()
                        .anyRequest().authenticated()
                )
                // ✅ Thêm JWT filter vào trước UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtAuthenticationFilter(jwtService),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                // ✅ Cho phép basic auth (chủ yếu để test)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
