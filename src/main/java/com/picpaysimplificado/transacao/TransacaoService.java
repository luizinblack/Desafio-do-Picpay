package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.Usuario;
import com.picpaysimplificado.usuario.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransacaoService {
    @Inject
    UsuarioService usuarioService;

    TransacaoRepository repository = new TransacaoRepository();
    @Transactional
    public Transacao criarTransacao(TransacaoDTO transacao) throws Exception {

        Usuario pagador = usuarioService.findUserById(transacao.pagadorId());
        Usuario beneficiario = usuarioService.findUserById(transacao.beneficiarioId());

        usuarioService.validarTransacao(pagador, transacao.quantia());
        Transacao novaTransacao =  new Transacao();
        novaTransacao.setQuantia(transacao.quantia());
        novaTransacao.setEnviar(pagador);
        novaTransacao.setBeneficiario(beneficiario);

        pagador.setSaldo(pagador.getSaldo().subtract(transacao.quantia()));
        beneficiario.setSaldo(beneficiario.getSaldo().add(transacao.quantia()));

        repository.persist(novaTransacao);

        return novaTransacao;
    }
    
}
