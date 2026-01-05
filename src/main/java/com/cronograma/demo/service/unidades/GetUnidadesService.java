package com.cronograma.demo.service.unidades;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cronograma.demo.dto.response.UnidadeResponseDTO;
import com.cronograma.demo.repository.UnidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetUnidadesService {
    private final UnidadeRepository unidadeRepository;

    public List<UnidadeResponseDTO> execute() {
        return unidadeRepository.findAll()
            .stream()
            .map(u -> new UnidadeResponseDTO(
                u.getId(),
                u.getNome(),
                u.getCep(),
                u.getCelular(),
                u.getEmail()
            ))
            .toList();
    }
}
