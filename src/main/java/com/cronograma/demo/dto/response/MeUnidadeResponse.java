package com.cronograma.demo.dto.response;

public record MeUnidadeResponse(
    Long id,
    String nome,
    String cep,
    String celular,
    String email 
) {}
