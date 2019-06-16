package web;


import dto.RawMat;
import functionality.RawMatFunctionality;
import functionality.interfaces.IRawMatFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rawMat")
public class RawMatResource {
    private IRawMatFunctionality rawMatFunc = new RawMatFunctionality();

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @PathParam("productId") int id) {
        try {
            return Response.ok(rawMatFunc.getRawMat(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(rawMatFunc.getAllRawMats()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(RawMat rawmat) {
        try {
            rawMatFunc.createRawMat(rawmat);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(RawMat rawmat) {
        try {
            rawMatFunc.updateRawMat(rawmat);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{prodId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("prodId") int id) {
        try {
            rawMatFunc.deleteRawMat(rawMatFunc.getRawMat(id));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}