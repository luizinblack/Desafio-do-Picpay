package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.Usuario;

import java.math.BigDecimal;

public record TransacaoDTO(BigDecimal quantia, Long pagadorId, Long beneficiarioId) {

}
