package web;

import dto.Product;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ProductResource.class);
    }

    @Test
    public void get() {
        Response response = target("products/1").request().get(Response.class);
        Product content = response.readEntity(Product.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(1, content.getProductId());
        assertEquals("Kebabeeee", content.getProductName());
    }

    @Test
    public void getAll() {
        Response response = target("products").request().get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    // @Test
    public void create() {
    }

    // @Test
    public void update() {
    }

    // @Test
    public void delete() {
    }
}