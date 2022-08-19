package com.account.statement.manager.configuration;

import com.account.statement.manager.constant.ApplicationConstants;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Configuration class for spring security integration.
 *
 * @author Sujith K V
 */
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Bean for {@link InMemoryUserDetailsManager}.
     * The method will create two users in the in memory database.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("user"))
                .roles(ApplicationConstants.ROLE_USER).build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin"))
                .roles(ApplicationConstants.ROLE_ADMIN).build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Bean for {@link SecurityFilterChain}.
     * The method is to configure role based authorization for different URLs.
     * We are setting the maximum session for a user as 1.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/statements/user/*")
                .hasAnyRole(ApplicationConstants.ROLE_USER, ApplicationConstants.ROLE_ADMIN)
                .antMatchers("/statements/**").hasRole(ApplicationConstants.ROLE_ADMIN).and().formLogin().and()
                .httpBasic().and().sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
        return http.build();
    }

    /**
     * Bean for {@link ServletListenerRegistrationBean}.
     * The method is to create bean for session event publisher
     */
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    /**
     * Bean for {@link PasswordEncoder}.
     * The method is to create bean for the password encoder.
     * We are using {@link BCryptPasswordEncoder} encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
