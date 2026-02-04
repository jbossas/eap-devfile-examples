package org.jboss.microprofile.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.microprofile.config.converter.type.MicroProfileCustomValue;

@Path("/converter")
public class ConverterResource {

    @Inject
    @ConfigProperty(name = "custom.converter.prop")
    private MicroProfileCustomValue microProfileCustomValue;

    @GET
    @Path("/value")
    public String customConverterProp() {
        return microProfileCustomValue.getName();
    }
}
