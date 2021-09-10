package com.br.gateway.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters configParameters, RouteDefinitionLocator locator) {
        var definitions = locator.getRouteDefinitions().collectList().block();

        definitions.stream().filter(
                        routeDefinition -> routeDefinition.getId()
                                .matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId();
                    configParameters.addGroup(name); //acessa as cofigs do swagger
                    GroupedOpenApi.builder()
                            .pathsToMatch("/" + name + "/**")
                            .group(name).build();
                });

        return new ArrayList<>();
    }

     Manter usuario
     Autenticar usuario
     Autorizar usuario
     Manter Serviço
     Gerar Orçamento /OS
     Manter termo de garantia
     Manter peças
     Efetuar pagamento
     Emitir cupom fiscal

}
