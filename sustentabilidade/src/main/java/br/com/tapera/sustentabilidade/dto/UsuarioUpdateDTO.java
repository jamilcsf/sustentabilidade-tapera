package br.com.tapera.sustentabilidade.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateDTO(
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String nome,

        @Email(message = "E-mail inválido")
        String email

        // Note que não colocamos a senha aqui ou, se colocarmos,
        // ela não tem a anotação @NotBlank, permitindo que seja nula (não alterada).
) {}
