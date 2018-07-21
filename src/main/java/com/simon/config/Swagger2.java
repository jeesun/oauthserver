package com.simon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by simon on 2017/2/25.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger2.title}")
    private String title;

    @Value("${swagger2.description}")
    private String description;

    @Value("${swagger2.terms-of-service-url}")
    private String termsOfServiceUrl;

    @Value("${swagger2.version}")
    private String version;

    @Value("${swagger2.contact.name}")
    private String contactName;

    @Value("${swagger2.contact.url}")
    private String contactUrl;

    @Value("${swagger2.contact.email}")
    private String contactEmail;

    @Value("${swagger2.license}")
    private String license;

    @Value("${swagger2.license-url}")
    private String licenseUrl;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.simon.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .license(license)
                .licenseUrl(licenseUrl)
                .build();
    }
}
