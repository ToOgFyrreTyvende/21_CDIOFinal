package web;

import dto.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }

    @Test
    public void getAll() {
        Response response = target("users").request().get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // User content = response.readEntity(User.class);
        //assertEquals( "hi", content);
    }

    @Test
    public void getAllRoles() {
        Response response = target("users/roles").request().get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    @Test
    public void get() {
        Response response = target("users/42").request().get(Response.class);
        User content = response.readEntity(User.class);

        System.out.println("response status is expected to be 200.");
        System.out.println("response status: " + response.getStatus());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        System.out.println("response content type is expected to be \"application/json\"");
        System.out.println("response content type: " + response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        System.out.println("response content expected to be user with: \n\t" +
                                   "userId:\t42\n\t" +
                                   "userName:\tCrash Test Dummy\n\t" +
                                   "role:\tAdmin (0)");
        assertEquals(42, content.getUserId());
        assertTrue("Crash Test Dummy".equals(content.getUserName()));
        assertEquals(0, content.getRole());
    }
}