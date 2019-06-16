package web;

import dto.Product;
import functionality.ProductFunctionality;
import functionality.interfaces.IProductFunctionality;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductResource {
    private IProductFunctionality prodFunc = new ProductFunctionality();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("{rawMatId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@DefaultValue("0") @PathParam("rawMatId") int id) {
        try {
            return Response.ok(prodFunc.getProduct(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            return Response.ok(prodFunc.getAllProducts()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        try {
            prodFunc.createProduct(product);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Product prod) {
        try {
            prodFunc.updateProduct(prod);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{rawMatId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("rawMatId") int id) {
        try {
            prodFunc.deleteProduct(prodFunc.getProduct(id));
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
