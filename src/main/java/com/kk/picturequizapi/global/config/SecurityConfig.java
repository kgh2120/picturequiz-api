package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.entity.UserRole;
import com.kk.picturequizapi.domain.users.service.UserService;
import com.kk.picturequizapi.global.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userDetailsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;
    private final RefreshTokenService refreshTokenService;

    AuthenticationManager authenticationManager;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
        authenticationManager = authenticationManagerBuilder.build();

        return httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/quiz/add").authenticated()
                .antMatchers(HttpMethod.DELETE,"/quiz/**").authenticated()
                .antMatchers(
                        "/signUp", "/login", "/refresh", "/h2-console/**", "/character", "/tag"
                        , "/quiz/**", "/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/comments").permitAll()
                .antMatchers("/admin/**").hasRole(UserRole.ROLE_ADMIN.getRole())
                .antMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authenticationManager(authenticationManager)
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager, jwtProvider,
                                refreshTokenService, authenticationFailureHandler())
                        , UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(jwtProvider, userDetailsService)
                        , JwtAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandlingFilter(),
                        JwtAuthorizationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(
                Arrays.asList("HEAD", "POST", "GET", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
