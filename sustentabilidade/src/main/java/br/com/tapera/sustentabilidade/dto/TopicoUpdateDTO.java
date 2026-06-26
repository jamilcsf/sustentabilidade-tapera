package br.com.tapera.sustentabilidade.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoUpdateDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,
        String conteudo
) {}