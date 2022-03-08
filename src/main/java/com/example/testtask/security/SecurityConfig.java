package com.example.testtask.security;

import com.example.testtask.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http                                 //Выключить security
                .csrf().disable();

//        http                                 //Включить security
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/orders/get").access("hasAuthority('EMPLOYEE')")
//                .antMatchers("/orders/getall").access("hasAuthority('EMPLOYEE')")
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//        http
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
