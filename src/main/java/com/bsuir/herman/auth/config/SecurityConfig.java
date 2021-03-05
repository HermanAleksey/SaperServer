package com.bsuir.herman.auth.config;

import com.bsuir.herman.auth.security.jwt.JwtConfigurer;
import com.bsuir.herman.auth.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String TEST_ENDPOINT = "/test/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private static final String USER_ENDPOINT = "/api/v1/users/**";
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
//    private static final String REGISTRATION_ENDPOINT = "/api/v1/auth/registration";


    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           .and()
                .authorizeRequests()
                .antMatchers(TEST_ENDPOINT).permitAll()
//                .antMatchers(REGISTRATION_ENDPOINT).permitAll()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(USER_ENDPOINT).hasRole("USER")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
           .and()
           .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
