package com.ivanbr.authserver.filter;

import com.ivanbr.authserver.util.JwtProvider;
import com.ivanbr.authserver.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private JwtProvider jwtProvider;
    private UserDetailsService userDetailsService;
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setJwtProviderService(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            setAuthentication(request);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException authenticationException) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, authenticationException);
        }
    }

    private void setAuthentication(HttpServletRequest request) {
        Optional<String> authorizationHeaderOptional = Optional.ofNullable(request.getHeader("Authorization"));

        authorizationHeaderOptional.filter(header -> header.contains("Bearer ")).ifPresent(header -> {
            String jwt = header.substring(7);
            if (jwtProvider.isTokenValid(jwt) && isAuthenticationEmpty()) {
                String username = jwtProvider.extractUsername(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(userDetails, "",
                                userDetails.getAuthorities()));
            }
        });
    }

    private boolean isAuthenticationEmpty() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
