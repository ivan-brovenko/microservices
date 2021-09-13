package com.ivanbr.authserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanbr.authserver.dto.ExceptionDto;
import com.ivanbr.authserver.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private JwtRequestFilter jwtRequestFilter;
    private ObjectMapper objectMapper;

    @Autowired
    public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .mvcMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return ((request, response, authException) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");

            ExceptionDto exceptionDto = new ExceptionDto();
            exceptionDto.setException(authException.getMessage());
            String exceptionString = objectMapper.writeValueAsString(exceptionDto);
            response.getWriter().print(exceptionString);
        });
    }
}
