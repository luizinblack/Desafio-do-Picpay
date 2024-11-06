package com.picpaysimplificado.usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Transactional
    public void create(UsuarioDTO usuario) throws Exception {
        if (repository.findByCpf(usuario.cpf()) != null) {
            throw new Exception("CPF já cadastrado!");
        }

        Usuario entity = new Usuario();
        entity.setNomeCompleto(usuario.nomeCompleto());
        entity.setIdade(usuario.idade());
        entity.setCpf(usuario.cpf());
        entity.setEmail(usuario.email());
        entity.setSenha(usuario.senha());
        entity.setSaldo(usuario.Saldo());
        entity.setTipoUsuario(usuario.tipoUsuario());
        repository.persist(entity);
    }

    public List<UsuarioDTO> selectAll() {
        List<Usuario> usuario = repository.listAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();
        for (Usuario c : usuario) {
            UsuarioDTO dto = new UsuarioDTO(c.getNomeCompleto(), c.getIdade(), c.getCpf(), c.getEmail(), c.getSenha(), c.getSaldo(),c.getTipoUsuario());
            usuarioDTOS.add(dto);
        }
        return usuarioDTOS;
    }

    @Transactional
    public void updateAll(Long id, UsuarioDTO usuario) {
        Usuario entity = repository.findById(id);
        entity.setNomeCompleto(usuario.nomeCompleto());
        entity.setIdade(usuario.idade());
        entity.setCpf(usuario.cpf());
        entity.setEmail(usuario.email());
        entity.setSenha(usuario.senha());
        repository.persist(entity);
    }

    @Transactional
    public void updatePartial(Long id, UsuarioDTO customer) {
        Usuario entity = repository.findById(id);
        entity.setIdade(customer.idade());
        entity.setCpf(customer.cpf());
        entity.setEmail(customer.email());
        repository.persist(entity);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        Usuario customer = repository.findById(id);
        if (customer == null) {
            throw new Exception("Registro não encontrado!");
        }
        repository.deleteById(id);
    }

    public UsuarioDTO selectById(Long id) {
        Usuario entity = repository.findById(id);
        UsuarioDTO dto = new UsuarioDTO(
                entity.getNomeCompleto(),
                entity.getIdade(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getSenha(),
                entity.getSaldo(),
                entity.getTipoUsuario());
        return dto;

    }
    public void validarTransacao(Usuario pagador, BigDecimal quantia) throws Exception {
        if(pagador.getTipoUsuario() == TipoUsuario.LOJISTA){
            throw new Exception("Usuario do tipo Lojista não está autorizado a realizar transação");
        }
        if(pagador.getSaldo().compareTo(quantia) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public Usuario findUserById(Long id){
        return this.repository.findById(id);
    }
}
