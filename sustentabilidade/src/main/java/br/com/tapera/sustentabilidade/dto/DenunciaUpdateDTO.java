package br.com.tapera.sustentabilidade.dto;

import jakarta.validation.constraints.NotBlank;

public record DenunciaUpdateDTO(
        @NotBlank(message = "A descrição não pode estar vazia")
        String descricao,
        String status // Ex: Aberta, Em Analise, Concluida
) {}
