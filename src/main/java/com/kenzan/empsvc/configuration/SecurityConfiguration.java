package com.kenzan.empsvc.configuration;

import com.kenzan.empsvc.auth.KenzanAuthenticationEntryPoint;
import com.kenzan.empsvc.auth.KenzanAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final KenzanAuthenticationEntryPoint unauthorizedExceptionHandler;

    public SecurityConfiguration(KenzanAuthenticationEntryPoint ueh){
        this.unauthorizedExceptionHandler = ueh;
    }
    @Bean
    public KenzanAuthenticationFilter kenzanAuthenticationFilter() {
        return new KenzanAuthenticationFilter();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedExceptionHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(kenzanAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
