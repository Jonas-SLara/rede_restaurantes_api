package com.cronograma.demo.service.unidades;

import org.springframework.stereotype.Service;

import com.cronograma.demo.dto.request.CreateUnidadeRequestDTO;
import com.cronograma.demo.dto.response.UnidadeResponseDTO;
import com.cronograma.demo.model.Unidade;
import com.cronograma.demo.repository.UnidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateUnidadeService {

    private final UnidadeRepository unidadeRepository;

    public UnidadeResponseDTO execute(CreateUnidadeRequestDTO dto){
        Unidade unidade = Unidade.builder()
            .celular(dto.celular())
            .cep(dto.cep())
            .email(dto.email())
            .nome(dto.nome())
            .build();

        Unidade saved = unidadeRepository.save(unidade);
        
        return new UnidadeResponseDTO(
            saved.getId(),
            saved.getNome(),
            saved.getCep(),
            saved.getCelular(),
            saved.getEmail()
        );
    }
}
