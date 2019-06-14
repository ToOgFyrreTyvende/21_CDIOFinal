package web;

import dto.User;
import dto.WeighedIngredientsBatches;
import functionality.WeighingFunctionality;
import functionality.interfaces.IWeighingFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/weighing")
public class WeighingResource {
    public IWeighingFunctionality wFunc = new WeighingFunctionality();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(wFunc.getAllWeighings()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("{weighingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("weighingId") int id) {

        try {
            return Response.ok(wFunc.getWeighing(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(WeighedIngredientsBatches weighing) {
        try {
            wFunc.createWeighing(weighing);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(WeighedIngredientsBatches weighing) {
        try {
            wFunc.updateWeighing(weighing);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{weighingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("weighingId") int id) {
        try {
            wFunc.deleteWeighing(wFunc.getWeighing(id));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
}
