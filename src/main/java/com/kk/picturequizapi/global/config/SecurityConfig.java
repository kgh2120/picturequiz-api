package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.service.UserService;
import com.kk.picturequizapi.global.security.CustomAuthenticationFailureHandler;
import com.kk.picturequizapi.global.security.JwtAuthenticationFilter;
import com.kk.picturequizapi.global.security.JwtAuthorizationFilter;
import com.kk.picturequizapi.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                .authorizeRequests()
                    .antMatchers("/signUp", "/login","/refresh").permitAll()
                    .antMatchers("/**").authenticated()
                    .anyRequest().authenticated()
                .and()
                .cors().and()
                .authenticationManager(authenticationManager)
//                .formLogin()
//                .failureHandler(authenticationFailureHandler())
//                .and()
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager,jwtProvider, refreshTokenService, authenticationFailureHandler())
                        ,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider,userDetailsService)
                        ,JwtAuthorizationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
