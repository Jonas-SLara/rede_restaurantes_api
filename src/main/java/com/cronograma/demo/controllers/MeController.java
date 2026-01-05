package com.cronograma.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cronograma.demo.dto.response.MeUnidadeResponse;
import com.cronograma.demo.dto.response.UserResponseDTO;
import com.cronograma.demo.model.UserDetailsAdapter;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.service.me.DeletMeUnidadeService;
import com.cronograma.demo.service.me.MeUnidadesServices;
import com.cronograma.demo.service.me.RegisterMeUnidadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {

    private final RegisterMeUnidadeService registerMeUnidadeService;
    private final MeUnidadesServices meUnidadesServices;
    private final DeletMeUnidadeService deletMeUnidadeService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> me(
        @AuthenticationPrincipal UserDetailsAdapter user){
        Usuario u = user.getUsuario();
        return ResponseEntity.ok().body(
            new UserResponseDTO(u.getUuid(), u.getName(), u.getEmail())
        );
    }

    //pegar o usuario atual, salvo no contexto e associa a uma unidade
    @PostMapping("/register")
    public ResponseEntity<?> registerMe(
            @RequestParam Long idUnidade,
            @AuthenticationPrincipal UserDetailsAdapter user ) {
        Usuario u = user.getUsuario();
        registerMeUnidadeService.execute(u, idUnidade);
        return ResponseEntity.noContent().build();
    }

    //listar todas as unidades do usuario logado

    @GetMapping("/unidades")
    public ResponseEntity<?> getMyUnidades(
            @AuthenticationPrincipal UserDetailsAdapter user) {
        List<MeUnidadeResponse> unidadeResponses = meUnidadesServices.execute(user.getUsuario());
        return ResponseEntity.ok().body(unidadeResponses);
    }
    
    @DeleteMapping("/unidades")
    public ResponseEntity<?> deleteMyUnidade(
            @AuthenticationPrincipal UserDetailsAdapter user,
            @RequestParam Long idUnidade){
        deletMeUnidadeService.execute(user.getUsuario(), idUnidade);
        return ResponseEntity.noContent().build();
    }
}
