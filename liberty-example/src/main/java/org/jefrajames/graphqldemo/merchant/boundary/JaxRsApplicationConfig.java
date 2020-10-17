package org.jefrajames.graphqldemo.merchant.boundary;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@ApplicationPath("/rest")
@OpenAPIDefinition(
        info = @Info(
                title = "EclipseCon 2020 GraphQL demo Open Liberty", 
                version = "1.0.0",
                contact = 
                        @Contact(
                                name = "JF James", 
                                email = "jefrajames@gmail.com",
                                url = "http://jefrajames.fr")
                ),
                servers = @Server(url = "/",description = "Localhost")
        )
public class JaxRsApplicationConfig extends Application {

}
