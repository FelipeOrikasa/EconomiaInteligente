package br.com.fiap.resource;

import br.com.fiap.beans.Projeto;
import br.com.fiap.bo.ProjetoBO;
import br.com.fiap.dao.ProjetoDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/projetos")
public class ProjetoResource {
    private ProjetoBO bo = new ProjetoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProjetos() {
        try {
            List<Projeto> projetos = bo.listarProjetosEmAndamento();
            return Response.ok(projetos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar projetos: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/alto-custo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProjetosAltoCusto() {
        try {
            List<Projeto> projetos = bo.listarProjetosAltoCusto();
            return Response.ok(projetos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao listar projetos de alto custo: " + e.getMessage())
                .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarProjeto(Projeto projeto) {
        try {
            bo.cadastrarProjeto(projeto);
            return Response.status(Response.Status.CREATED).entity(projeto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao cadastrar projeto: " + e.getMessage())
                .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarProjeto(@PathParam("id") int id, Projeto projeto) {
        try {
            projeto.setIdProjeto(id);
            bo.cadastrarProjeto(projeto);
            return Response.ok(projeto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Erro ao atualizar projeto: " + e.getMessage())
                .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removerProjeto(@PathParam("id") int id) {
        try {
        	ProjetoDAO dao = new ProjetoDAO();
            dao.remover(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao remover projeto: " + e.getMessage())
                .build();
        }
    }
}