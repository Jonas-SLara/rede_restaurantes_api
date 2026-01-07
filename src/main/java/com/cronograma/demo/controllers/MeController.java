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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
@Tag(
    name = "operações ralacionadas ao usuario autenticado",
    description = "usuario autenticado com token"
)
public class MeController {

    private final RegisterMeUnidadeService registerMeUnidadeService;
    private final MeUnidadesServices meUnidadesServices;
    private final DeletMeUnidadeService deletMeUnidadeService;

    @Operation(
        summary = "ver informações do usuario que esta autenticado"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<UserResponseDTO> me(
        @AuthenticationPrincipal UserDetailsAdapter user){
        Usuario u = user.getUsuario();
        return ResponseEntity.ok().body(
            new UserResponseDTO(u.getUuid(), u.getName(), u.getEmail())
        );
    }

    @Operation(summary = "associar uma unidade ao usuario autenticado")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<?> registerMe(
            @RequestParam Long idUnidade,
            @AuthenticationPrincipal UserDetailsAdapter user ) {
        Usuario u = user.getUsuario();
        registerMeUnidadeService.execute(u, idUnidade);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "listar as unidades do usuario que está autenticado")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/unidades")
    public ResponseEntity<?> getMyUnidades(
            @AuthenticationPrincipal UserDetailsAdapter user) {
        List<MeUnidadeResponse> unidadeResponses = meUnidadesServices.execute(user.getUsuario());
        return ResponseEntity.ok().body(unidadeResponses);
    }
    
    @Operation(summary = "deletar uma unididade a que o usuario logado pertence")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/unidades")
    public ResponseEntity<?> deleteMyUnidade(
            @AuthenticationPrincipal UserDetailsAdapter user,
            @RequestParam Long idUnidade){
        deletMeUnidadeService.execute(user.getUsuario(), idUnidade);
        return ResponseEntity.noContent().build();
    }
}
