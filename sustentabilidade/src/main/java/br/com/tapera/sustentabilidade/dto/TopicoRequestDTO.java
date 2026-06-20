package br.com.tapera.sustentabilidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "O conteúdo é obrigatório")
        String conteudo,

        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId
) {}
