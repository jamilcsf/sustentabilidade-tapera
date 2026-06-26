package br.com.tapera.sustentabilidade.dto;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String senha
) {}
