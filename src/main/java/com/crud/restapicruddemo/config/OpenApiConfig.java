package com.crud.restapicruddemo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "User API ",
                description = "Implementation of  CRUD operations on User having fields `name` and `email` ",
                summary = "This User API  will ADD, DELETE, UPDATE and READ User information ",
                version = "1.0.0"),
        servers = {
                @Server(
                        description = "Testing",
                        url = "http://localhost:9090"
                )

        }

)
public class OpenApiConfig {
}
