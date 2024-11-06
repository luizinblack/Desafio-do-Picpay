package com.picpaysimplificado.usuario;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuario")
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    public Response create(@Valid UsuarioDTO usuario) {
        try {
            service.create(usuario);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(422).entity("{ \"mensagem\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    public Response read() {
        List<UsuarioDTO> usuarios = service.selectAll();
        return Response.status(Response.Status.OK).entity(usuarios).build();
    }

    @GET
    @Path("/{id}")
    public Response selectById(Long id) {
        try {
            UsuarioDTO customer = service.selectById(id);
            return Response.status(Response.Status.OK).entity(customer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"mensagem\": \"Registro não encontrado\"}").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateAll(Long id, @Valid UsuarioDTO usuario) {
        try {
            service.updateAll(id, usuario);
            String msg = "{ \"msg\": \"SUCESSO\"}";
            return Response.status(Response.Status.OK).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"mensagem\": \"Registro não encontrado\"}").build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response updatePartial(Long id, @Valid UsuarioDTO usuario) {
        service.updatePartial(id, usuario);
        String msg = "{ \"mensagem\": \"SUCESSO\"}";
        return Response.status(Response.Status.OK).entity(msg).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        try {
            service.delete(id);
            String msg = "{ \"mensagem\": \"SUCESSO\"}";
            return Response.status(Response.Status.NO_CONTENT).entity(msg).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("{ \"mensagem\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
