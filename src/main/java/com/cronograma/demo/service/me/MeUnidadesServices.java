package com.cronograma.demo.service.me;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cronograma.demo.dto.response.MeUnidadeResponse;
import com.cronograma.demo.model.Usuario;
import com.cronograma.demo.repository.UnidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeUnidadesServices {

    private final UnidadeRepository unidadeRepository;

    public List<MeUnidadeResponse> execute(Usuario usuario){
        return unidadeRepository.findAllByUsuarioId(usuario.getUuid())
            .stream()
            .map(u -> new MeUnidadeResponse(
                u.getId(),
                u.getNome(),
                u.getCep(),
                u.getCelular(),
                u.getEmail()
            ))
            .toList();
    }
}
