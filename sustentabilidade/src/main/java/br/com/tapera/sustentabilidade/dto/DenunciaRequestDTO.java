package br.com.tapera.sustentabilidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DenunciaRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "O tipo é obrigatório")
        String tipo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotBlank(message = "A localização é obrigatória")
        String localizacao,

        String referencia,

        @NotNull(message = "A latitude é obrigatória")
        Double lat,

        @NotNull(message = "A longitude é obrigatória")
        Double lng
) {}