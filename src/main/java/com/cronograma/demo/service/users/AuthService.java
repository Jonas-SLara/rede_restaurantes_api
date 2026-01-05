package com.cronograma.demo.service.users;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cronograma.demo.dto.request.LoginRequestDTO;
import com.cronograma.demo.dto.response.LoginResponseDTO;
import com.cronograma.demo.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDTO login(LoginRequestDTO dto){
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
            )
        );
        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateTokenDefault(user);
        return new LoginResponseDTO(token);
    }
}
