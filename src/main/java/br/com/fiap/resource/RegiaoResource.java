package br.com.fiap.resource;

import br.com.fiap.beans.Regiao;
import br.com.fiap.bo.RegiaoBO;
import br.com.fiap.dao.RegiaoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/regioes")
public class RegiaoResource {
    private RegiaoBO bo = new RegiaoBO();
    private RegiaoDAO dao = new RegiaoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarRegioes() {
        try {
            List<Regiao> regioes = bo.listarRegioesMeteopolitanas();
            return Response.ok(regioes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar regiões: " + e.getMessage())
                .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarRegiao(Regiao regiao) {
        try {
            bo.cadastrarRegiao(regiao);
            return Response.status(Response.Status.CREATED).entity(regiao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao cadastrar região: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarRegiao(@PathParam("id") int id, Regiao regiao) {
        try {
            regiao.setIdRegiao(id);
            bo.atualizarRegiao(regiao);
            return Response.ok(regiao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao atualizar região: " + e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removerRegiao(@PathParam("id") int id) {
        try {
            dao.remover(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao remover região: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarRegiaoPorId(@PathParam("id") int id) {
        try {
        	Regiao regiao = dao.buscarPorId(id);
            if (regiao != null) {
                return Response.ok(regiao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Região não encontrada")
                    .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao buscar região: " + e.getMessage())
                .build();
        }
    }
}