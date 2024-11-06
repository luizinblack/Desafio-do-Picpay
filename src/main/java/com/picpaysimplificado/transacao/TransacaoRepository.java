package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransacaoRepository implements PanacheRepositoryBase<Transacao, Long> {

}
