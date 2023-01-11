package com.flores.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration 
@EnableSwagger2
public class SwaggerConfig {

    @SuppressWarnings("unused")
	private ApiInfo apiInfo() {
        return new ApiInfo(
                "VENTAS API especificaciones",
                "VENTAS Spring REST API",
                "",
                "",
                null,
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
    
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2)
    			.select()
    			.apis(
    					RequestHandlerSelectors
    					.basePackage("com.flores.controller"))
    			.paths(PathSelectors.any())
    			.build();
    }
    
    @SuppressWarnings("unused")
	private List<ApiKey> securitySchemes() {

        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        result.add(apiKey);
        return result;
    }

    @SuppressWarnings("unused")
	private List<SecurityContext> securityContexts() {
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/api/.*"));
        result.add(getContextByPath("/api/vehicles/.*"));
        result.add(getContextByPath("/api/vehicle/.*"));
        return result;
    }

    @SuppressWarnings("deprecation")
	private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }
}
