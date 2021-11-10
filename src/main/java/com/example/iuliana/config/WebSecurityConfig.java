package com.example.iuliana.config;

import com.example.iuliana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    /*
    @Bean
    public UserDetailsService inMemoryUsers(){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$H/ZjmHUARPKdb8DpRIJSx.DfGy.aH3qW/iizFLDAe9kC3mRbN365a")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$H/ZjmHUARPKdb8DpRIJSx.DfGy.aH3qW/iizFLDAe9kC3mRbN365a")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
    */

   /*
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$H/ZjmHUARPKdb8DpRIJSx.DfGy.aH3qW/iizFLDAe9kC3mRbN365a")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$12$H/ZjmHUARPKdb8DpRIJSx.DfGy.aH3qW/iizFLDAe9kC3mRbN365a")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager jdbcUsers = new JdbcUserDetailsManager(dataSource);
        jdbcUsers.createUser(user);
        jdbcUsers.createUser(admin);
        return  jdbcUsers;
    }
    */

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
