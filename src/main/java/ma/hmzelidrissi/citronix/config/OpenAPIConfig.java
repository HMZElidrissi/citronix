package ma.hmzelidrissi.citronix.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * To access the OpenAPI documentation, go to <a
 * href="http://localhost:8084/swagger-ui.html">...</a> The @OpenAPIDefinition annotation is used to
 * define the metadata of the OpenAPI specification. The @SecurityScheme annotation is used to
 */
@OpenAPIDefinition(
    info =
        @Info(
            title = "Citronix",
            version = "v1",
            description = "Farm management system",
            contact = @Contact(name = "Hamza El Idrissi", url = "https://hmzelidrissi.ma"),
            license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT"),
            termsOfService = "some terms of service"),
    servers = {
      @Server(url = "http://localhost:8084", description = "Local server"),
    })
public class OpenAPIConfig {}
