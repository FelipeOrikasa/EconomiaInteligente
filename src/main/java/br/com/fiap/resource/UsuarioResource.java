package br.com.fiap.resource;

import br.com.fiap.beans.Usuario;
import br.com.fiap.bo.UsuarioBO;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {

    private UsuarioBO usuarioBO;

    public UsuarioResource() {
        try {
            usuarioBO = new UsuarioBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioBO.listarUsuarios();
            return Response.ok(usuarios).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao listar usuários").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuario(@PathParam("id") int id) {
        try {
            Usuario usuario = usuarioBO.buscarUsuario(id);
            if (usuario == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
            }
            return Response.ok(usuario).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao buscar usuário").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirUsuario(Usuario usuario, @Context UriInfo uriInfo) {
        try {
            String resultado = usuarioBO.inserirUsuario(usuario);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(String.valueOf(usuario.getIdUsuario()));
            return Response.created(builder.build()).entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao inserir usuário").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario, @PathParam("id") int id) {
        try {
            usuario.setIdUsuario(id);
            String resultado = usuarioBO.atualizarUsuario(usuario);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao atualizar usuário").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarUsuario(@PathParam("id") int id) {
        try {
            String resultado = usuarioBO.deletarUsuario(id);
            return Response.ok().entity(resultado).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Erro ao deletar usuário").build();
        }
    }
}
