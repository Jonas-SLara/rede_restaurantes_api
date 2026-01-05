package com.cronograma.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronograma.demo.dto.response.UserResponseDTO;
import com.cronograma.demo.model.UserDetailsAdapter;
import com.cronograma.demo.model.Usuario;

@RestController
@RequestMapping("/me")
public class MeController {

    @GetMapping
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal UserDetailsAdapter user){
        Usuario u = user.getUsuario();
        return ResponseEntity.ok().body(
            new UserResponseDTO(u.getUuid(), u.getName(), u.getEmail())
        );
    }
}
