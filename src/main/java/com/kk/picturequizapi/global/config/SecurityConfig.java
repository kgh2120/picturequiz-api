package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.domain.users.service.UserService;
import com.kk.picturequizapi.global.jwt.JwtAuthenticationFilter;
import com.kk.picturequizapi.global.jwt.JwtAuthorizationFilter;
import com.kk.picturequizapi.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userDetailsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;

    AuthenticationManager authenticationManager;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder);
        authenticationManager = authenticationManagerBuilder.build();

        return httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/signUp", "/login").permitAll()
                    .antMatchers("/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                .cors().and()
                .authenticationManager(authenticationManager)
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager,jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider,userDetailsService),JwtAuthorizationFilter.class)
                .build();
    }
}
