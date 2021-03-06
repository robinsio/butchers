package org.robins.io.butchers.config;

import org.robins.io.butchers.persistence.service.UserService;
import org.robins.io.butchers.web.security.AuthenticationTokenProcessingFilter;
import org.robins.io.butchers.web.security.Http401UnauthorizedEntryPoint;
import org.robins.io.butchers.web.security.SaltedSHA256PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserService userService;

    @Autowired
    private SaltedSHA256PasswordEncoder saltedSHA256PasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(saltedSHA256PasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .addFilterBefore(authenticationTokenProcessingFilter, LogoutFilter.class)
            .authorizeRequests()
            .antMatchers("/api/user/authenticate").permitAll()
            .antMatchers("/api/**").hasRole("USER")
            .anyRequest().permitAll();

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public SaltedSHA256PasswordEncoder saltedSHA256PasswordEncoder() throws Exception {
        return new SaltedSHA256PasswordEncoder("secret");
    }
}