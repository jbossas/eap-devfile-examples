package org.jboss.examples;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Run unit tests
 */
public class GettingStartedServiceTest {

     GettingStartedService service = new  GettingStartedService();

    @Test
    public void testService() {
        String result = service.hello("World");
        assertEquals("Hello 'World'.", result);

        result = service.hello("Monde");
        assertEquals("Hello 'Monde'.", result);
    }
}

