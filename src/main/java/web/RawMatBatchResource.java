package web;


import dto.RawMatBatch;
import functionality.RawMatBatchFunctionality;
import functionality.interfaces.IRawMatBatchFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rawMatBatches")
public class RawMatBatchResource {
    private IRawMatBatchFunctionality rawMatBatchFunc = new RawMatBatchFunctionality();

    @GET
    @Path("{rmbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @PathParam("rmbId") int id) {
        try {
            return Response.ok(rawMatBatchFunc.getRawMatBatch(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(rawMatBatchFunc.getAllRawMatBatches()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RawMatBatch rawmat) {
        try {
            rawMatBatchFunc.createRawMatBatch(rawmat);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(RawMatBatch rawmatBatch) {
        try {
            rawMatBatchFunc.updateRawMatBatch(rawmatBatch);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{rmbId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("rmbId") int id) {
        try {
            rawMatBatchFunc.deleteRawMatBatch(rawMatBatchFunc.getRawMatBatch(id));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
