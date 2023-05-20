package br.com.undefined.api.config;


import java.io.IOException;

import br.com.undefined.api.services.TokenService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationFilter /*extends OncePerRequestFilter*/ {

    @Autowired
    private TokenService tokenService;

    /*@SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = getToken(request);

        if (token != null){
            var usuario = tokenService.getValidateUser(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        //chamar o proximo
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization"); // Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")){
            return null;
        }

        return header.replace("Bearer ", "");
    }*/

}