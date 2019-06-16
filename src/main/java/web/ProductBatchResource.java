package web;

import dto.ProductBatch;
import functionality.ProductBatchFunctionality;
import functionality.interfaces.IProductBatchFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/productBatches")
public class ProductBatchResource {
    private IProductBatchFunctionality prodFunc = new ProductBatchFunctionality();

    @GET
    @Path("{productBatchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @PathParam("productBatchId") int id) {
        try {
            return Response.ok(prodFunc.getProdBatch(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(prodFunc.getAllProdBatches()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProductBatch productBatch) {
        try {
            prodFunc.createProductBatch(productBatch);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(ProductBatch prod) {
        try {
            prodFunc.updateProdBatch(prod);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{prodBatchId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("prodBatchId") int id) {
        try {
            prodFunc.deleteProdBatch(prodFunc.getProdBatch(id));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
