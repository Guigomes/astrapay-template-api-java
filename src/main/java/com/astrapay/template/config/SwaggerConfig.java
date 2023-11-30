package com.astrapay.template.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	  @Bean
	    public GroupedOpenApi controllerApi() {
	        return GroupedOpenApi.builder()
	                .group("V1")
	                .packagesToScan("com.astrapay.template.controller")
	                .build();
	    }
}