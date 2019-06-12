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
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @PathParam("productId") int id) {

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
    public Response create(ProductBatch product) {
        try {
            prodFunc.createProductBatch(product);
            return Response.ok().build();
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
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{prodId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("prodId") int id) {
        try {
            prodFunc.deleteProdBatch(prodFunc.getProdBatch(id));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }
}
