package com.picpaysimplificado.usuario;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, Long> {

    public Usuario findByCpf(String cpf){
        return find("cpf", cpf).firstResult();
    }

    public Usuario findByEmail(String email){return find("email", email).firstResult();}
}
