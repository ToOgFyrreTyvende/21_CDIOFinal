package web;

import dto.ProductBatch;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductBatchResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ProductBatchResource.class);
    }

    @Test
    public void get() {
        Response response = target("productBatches/42").request().get(Response.class);
        ProductBatch content = response.readEntity(ProductBatch.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(42, content.getProdBatchId());
        assertEquals("Kebabeeee", content.getName());
        assertEquals(1, content.getProdId());
        assertEquals(0, content.getStatus());
    }

    @Test
    public void getAll() {
        Response response = target("productBatches").request().get(Response.class);

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