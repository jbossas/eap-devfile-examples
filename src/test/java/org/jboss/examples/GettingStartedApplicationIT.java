package org.jboss.examples;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Run integration tests against the server and the deployed application.
 */
@RunAsClient
@RunWith(Arquillian.class)
public class GettingStartedApplicationIT {

    @Test
    public void testHelloEndpoint() {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client
                    .target(URI.create("http://localhost:8080/"))
                    .path("/hello/World")
                    .request()
                    .get();

            assertEquals(200, response.getStatus());
            assertEquals("Hello 'World'.", response.readEntity(String.class));

        } finally {
            client.close();
        }
    }
}
