package com.rakbank.purchaseservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Purchase Service",
                description = "Purchase Service will provide all API's required for student fee purchase management",
                version="1.0",
                license = @License(
                        name = "Apache 2.0",
                        url="http://springdoc.org"
                ),
                termsOfService = "http://swagger.io/terms/"
        ),
        servers = {
                @Server(
                        description = "Local",
                        url="http://localhost:9082"
                )
        }
)
@Configuration
public class OpenAPIConfig {

}
