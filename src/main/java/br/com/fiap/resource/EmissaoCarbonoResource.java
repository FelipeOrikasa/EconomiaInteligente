package br.com.fiap.resource;

import br.com.fiap.beans.EmissaoCarbono;
import br.com.fiap.bo.EmissaoCarbonoBO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/emissoes")
public class EmissaoCarbonoResource {
    private EmissaoCarbonoBO bo;

    public EmissaoCarbonoResource() {
        this.bo = new EmissaoCarbonoBO();
    }

    // Create
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(EmissaoCarbono emissao) {
        try {
            bo.cadastrar(emissao);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Read All
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEmissoes() {
        try {
            List<EmissaoCarbono> emissoes = bo.listarEmissoes();
            return Response.ok(emissoes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Read High Emissions
    @GET
    @Path("/altas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEmissoesAltas() {
        try {
            List<EmissaoCarbono> emissoes = bo.listarEmissoesAltas();
            return Response.ok(emissoes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Read by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            EmissaoCarbono emissao = bo.buscarPorId(id);
            if (emissao != null) {
                return Response.ok(emissao).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Update
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(@PathParam("id") int id, EmissaoCarbono emissao) {
        try {
            // Ensure ID consistency
            emissao.setIdEmissao(id);
            bo.atualizar(emissao);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Delete
    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        try {
            bo.remover(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }
}