package com.example.market.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizacaoUsuario(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @Size(
                min = 6,
                message = "A nova senha deve ter pelo menos 6 caracteres"
        )
        String senha

) {
    public AtualizacaoUsuario {
        nome = nome == null ? null : nome.trim();
        email = email == null ? null : email.trim();

        if (senha != null && senha.isBlank()) {
            senha = null;
        }
    }
}