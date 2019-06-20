package web;

import dto.WeighedIngredientsBatches;
import functionality.WeighingFunctionality;
import functionality.interfaces.IFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weighing")
public class WeighingResource {
    private IFunctionality wFunc = new WeighingFunctionality();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(wFunc.getAllDTOs()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{weighingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("weighingId") int id) {
        try {
            return Response.ok(wFunc.getDTO(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(WeighedIngredientsBatches weighing) {
        try {
            wFunc.createDTO(weighing);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(WeighedIngredientsBatches weighing) {
        try {
            wFunc.updateDTO(weighing);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{weighingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("weighingId") int id) {
        try {
            wFunc.deleteDTO(wFunc.getDTO(id));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
