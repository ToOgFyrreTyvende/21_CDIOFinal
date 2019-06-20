package web;

import dto.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }

    // @Test
    public void getAll() {
        Response response = target("users").request().get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    // @Test
    public void getAllRoles() {
        Response response = target("users/roles").request().get(Response.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    // @Test
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
        assertEquals("Crash Test Dummy", content.getUserName());
        assertEquals(0, content.getRole());
    }

    // @Test
    public void CRUD() {
        User testUser = new User();
        testUser.setUserId(42);
        testUser.setUserName("Crash Test Dummy");
        testUser.setIni("CTD");
        testUser.setCpr("123456-7890");
        testUser.setRole(0);

        // Check user does not exist
        Response userNotFound = target("users/42").request().get(Response.class);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), userNotFound.getStatus());

        // Create user
        Response createUser = target("users")
                .request().post(Entity.entity(testUser, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), createUser.getStatus());

        // Update user
        testUser.setUserName("Updated Test Dummy");
        Response updateUser = target("users").request().put(Entity.entity(testUser, MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), updateUser.getStatus());

        // Delete user
        Response deleteUser = target("users/42").request().delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteUser.getStatus());
    }
}