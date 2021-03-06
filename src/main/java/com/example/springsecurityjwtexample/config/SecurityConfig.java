package com.example.springsecurityjwtexample.config;

import com.example.springsecurityjwtexample.domain.ROLE;
import com.example.springsecurityjwtexample.filter.CustomAuthenticationFilter;
import com.example.springsecurityjwtexample.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        var authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
        var authenticationManager = authenticationManagerBuilder.build();
        return httpSecurity.authenticationManager(authenticationManager)
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(STATELESS)
            .and()
            .authorizeRequests().antMatchers("/login/**").permitAll()
            .and()
            .authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority(ROLE.USER.toString())
            .and()
            .authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/save/**").hasAnyAuthority(ROLE.ADMIN.toString())
            .and()
            .addFilter(new CustomAuthenticationFilter(authenticationManager))
            .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
