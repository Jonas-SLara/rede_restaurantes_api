package com.cronograma.demo.dto.response;

public record UnidadeResponseDTO(
    Long id,
    String nome,
    String cep,
    String celular,
    String email
) {}
