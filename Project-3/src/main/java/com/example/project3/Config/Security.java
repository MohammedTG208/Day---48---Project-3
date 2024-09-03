package com.example.project3.Config;

import com.example.project3.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeRequests()
                .requestMatchers("/api/v1/cus/register","/api/v1/emp/register").permitAll()
                .requestMatchers("/api/v1/cus/update/","/api/v1/cus/delete","/api/v1/user/get-user-info","/api/v1/acc/add-new-account","/api/v1/acc/delete","/api/v1/acc/update","/api/v1/acc/active-account","/api/v1/acc/transfer-to/","/api/v1/acc/deposit/","/api/v1/acc/Withdraw/","/api/v1/acc/get/cus/account").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/cus/get-all-cus-info","/api/v1/user/get-all-users-for-admin","/api/v1/emp/get-all-for-admin","/api/v1/emp/delete/","/api/v1/acc/block-account/","/api/v1/emp/delete/").hasAuthority("ADMIN")
                //here is duplicate check wean get the data
                .requestMatchers("/api/v1/emp/update").hasAuthority("EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }
}
