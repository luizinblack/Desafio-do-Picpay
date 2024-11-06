package com.picpaysimplificado.transacao;

import com.picpaysimplificado.usuario.UsuarioDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/transacao")
public class TransacaoResource {
    @Inject
    TransacaoService service;

    @POST
    public Response criarTransacao(TransacaoDTO transacaoDTO) throws Exception {
        try {
            Transacao novaTransacao = service.criarTransacao(transacaoDTO);
            return Response.status(Response.Status.OK).entity(novaTransacao).build();
        } catch (Exception e) {
            return Response.status(422).entity("{ \"mensagem\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
