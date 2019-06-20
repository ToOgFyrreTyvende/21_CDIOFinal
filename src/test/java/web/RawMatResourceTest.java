package web;

import dto.RawMat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RawMatResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(RawMatResource.class);
    }

    // @Test
    public void get() {
        Response response = target("rawMat/42").request().get(Response.class);
        RawMat content = response.readEntity(RawMat.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(42, content.getRawMatID());
        assertEquals("dråbelyd+", content.getRawMatName());
    }

    // @Test
    public void getAll() {
        Response response = target("rawMat").request().get(Response.class);

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