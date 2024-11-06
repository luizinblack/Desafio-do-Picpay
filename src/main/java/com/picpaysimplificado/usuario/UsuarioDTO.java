package com.picpaysimplificado.usuario;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório!")
        String nomeCompleto,
        @Min(value = 18, message = "Idade não permitida!")
        Integer idade,
        @Size(min = 11, max = 11, message = "Obrigatório 11 caracteres!")
        @NotBlank(message = "O CPF é obrigatório!")
        String cpf,
        @Email(message = "Formato inválido!")
        String email,
        @Size(min = 6, max = 10, message = "A senha deve conter no mínimo 6 caracteres e no máximo 10!")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "A senha deve conter apenas letras é números")
        String senha,
        BigDecimal Saldo,
        TipoUsuario tipoUsuario
) {
}
