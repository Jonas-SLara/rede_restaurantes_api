package com.cronograma.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUnidadeRequestDTO(
    @NotBlank
    String nome,

    @NotBlank
    String cep,

    @NotBlank
    String celular,

    @Email
    @NotBlank
    String email
) {}
