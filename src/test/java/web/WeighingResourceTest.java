package web;

import dto.WeighedIngredientsBatches;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeighingResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(WeighingResource.class);
    }

    @Test
    public void get() {
        Response response = target("weighing/42").request().get(Response.class);
        WeighedIngredientsBatches content = response.readEntity(WeighedIngredientsBatches.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(42, content.getWeighedIngredientId());
        assertEquals(42, content.getRawMatBatchId());
        assertEquals(42, content.getProdBatchId());
        assertEquals(42, content.getUserId());
        assertEquals(2.1, content.getTara());
        assertEquals(4.2, content.getNetto());
    }

    @Test
    public void getAll() {
        Response response = target("weighing").request().get(Response.class);

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