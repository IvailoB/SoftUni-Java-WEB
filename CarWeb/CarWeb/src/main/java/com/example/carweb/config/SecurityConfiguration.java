package com.example.carweb.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository) throws Exception {
        http.
                // defines which pages will be authorized
                        authorizeHttpRequests().
                // allow access to all static files (images, CSS, js)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // the URL-s below are available for all users - logged in and anonymous
                        requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll().
                anyRequest().authenticated().
                and().
                // configure login with HTML form
                        formLogin().
                loginPage("/users/login").
                // the names of the user name, password input fields in the custom login form
                        usernameParameter("username").
                passwordParameter("password").
                // where do we go after login
                        defaultSuccessUrl("/home").//use true argument if you always want to go there, otherwise go to previous page
                failureForwardUrl("/users/login-error").
                and().logout().//configure logout
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").//go to homepage after logout
                invalidateHttpSession(true).
                and().
                securityContext().
                securityContextRepository(securityContextRepository);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
