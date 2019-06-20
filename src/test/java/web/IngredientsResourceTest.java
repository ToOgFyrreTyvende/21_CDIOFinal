package web;

import dto.ProductIngredient;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientsResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(IngredientsResource.class);
    }

    // @Test
    public void get() {
        Response response = target("ingredients/32").request().get(Response.class);
        ProductIngredient content = response.readEntity(ProductIngredient.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(32, content.getProductIngredientId());
        assertEquals(1, content.getProductId());
        assertEquals(1, content.getRawMatId());
        assertEquals(11.0, content.getAmount());
        assertEquals(0.0, content.getTolerance());
    }

    // @Test
    public void getAll() {
        Response response = target("ingredients").request().get(Response.class);

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