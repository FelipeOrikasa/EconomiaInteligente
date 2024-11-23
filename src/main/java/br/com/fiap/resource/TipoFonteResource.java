package br.com.fiap.resource;

import br.com.fiap.beans.Projeto;
import br.com.fiap.beans.TipoFonte;
import br.com.fiap.bo.TipoFonteBO;
import br.com.fiap.dao.ProjetoDAO;
import br.com.fiap.dao.TipoFonteDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/fontes")
public class TipoFonteResource {
    private TipoFonteBO bo = new TipoFonteBO();
    private TipoFonteDAO dao = new TipoFonteDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFontes() {
        try {
            List<TipoFonte> fontes = bo.listarFontesRenovaveis();
            return Response.ok(fontes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar fontes: " + e.getMessage())
                .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarFonte(TipoFonte fonte) {
        try {
            bo.cadastrarFonte(fonte);
            return Response.status(Response.Status.CREATED).entity(fonte).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao cadastrar fonte: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarFonte(@PathParam("id") int id, TipoFonte fonte) {
        try {
            fonte.setIdTipoFonte(id);
            bo.atualizarFonte(fonte);
            return Response.ok(fonte).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao atualizar fonte: " + e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removerFonte(@PathParam("id") int id) {
        try {
            dao.remover(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao remover fonte: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarFontePorId(@PathParam("id") int id) {
        try {
        	TipoFonte fonte = dao.buscarPorId(id);
            if (fonte != null) {
                return Response.ok(fonte).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Fonte não encontrada")
                    .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao buscar fonte: " + e.getMessage())
                .build();
        }
    }
}