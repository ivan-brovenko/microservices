package com.ivanbr.authserver.resource;

import com.ivanbr.authserver.dto.JwtDto;
import com.ivanbr.authserver.dto.UserDto;
import com.ivanbr.authserver.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserResource {
    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDto> login(@RequestBody @Valid UserDto userDto) {
        System.out.println("inside login");
        String jwt = userDetailsService.login(userDto.getUsername(), userDto.getPassword());
        JwtDto jwtDto = new JwtDto(jwt);

        return ResponseEntity.status(HttpStatus.OK).body(jwtDto);
    }
}
