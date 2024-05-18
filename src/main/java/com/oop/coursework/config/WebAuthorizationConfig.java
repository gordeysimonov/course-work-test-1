package com.oop.coursework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebAuthorizationConfig {

    GrantedAuthority g1 = () -> "READ";
    GrantedAuthority g2 = new SimpleGrantedAuthority("READ");

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
        );
/*
        UserDetails user = User.withUsername("Jessy")
                .password("Jessy123")
                .authorities("READ")
                .build();

        var userDetailsService = new InMemoryUserDetailsManager(user);
        http.userDetailsService(userDetailsService);

 */
        return http.build();
    }

}
