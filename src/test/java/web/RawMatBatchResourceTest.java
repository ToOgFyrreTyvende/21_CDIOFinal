package web;

import dto.RawMatBatch;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RawMatBatchResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(RawMatBatchResource.class);
    }

    // @Test
    public void get() {
        Response response = target("rawMatBatches/42").request().get(Response.class);
        RawMatBatch content = response.readEntity(RawMatBatch.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(42, content.getRmbId());
        assertEquals(42, content.getRawMatId());
        assertEquals(420.42, content.getAmount());
        assertEquals("dråbelyd+", content.getName());
        assertEquals("Gruppe 42/2", content.getSupplier());
    }

    // @Test
    public void getAll() {
        Response response = target("rawMatBatches").request().get(Response.class);

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