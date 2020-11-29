package com.ivanbr.authserver.service;

import com.ivanbr.authserver.dto.UserDto;
import com.ivanbr.authserver.exception.UserDoesNotExist;
import com.ivanbr.authserver.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private static final String USERS_URL = "http://user-server/users/";

    private RestTemplate restTemplate;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = loadUserFromUserServer(username);

        return org.springframework.security.core.userdetails.User.withUsername(userDto.getUsername())
                .password(userDto.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(new ArrayList<>())
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public String login(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authentication);

        return jwtProvider.createToken(username);
    }

    public UserDto loadUserFromUserServer(String username) {
        ResponseEntity<UserDto> responseEntity = restTemplate.exchange(USERS_URL + username,
                HttpMethod.GET, null, new ParameterizedTypeReference<UserDto>() {
                });
        return Optional.ofNullable(responseEntity.getBody()).orElseThrow(() ->
                new UserDoesNotExist("User with username:" + username + " does not exist."));
    }
}
