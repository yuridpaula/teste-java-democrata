package com.democrata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value(value = "${basePath}" + "${basePath.swagger}")
	String basePath;

	@Value(value = "${api.version}")
	String version;

	@Value(value = "${swagger.title}")
	String title;

	@Value(value = "${swagger.description}")
	String description;

	@Value(value = "${swagger.license}")
	String license;

	@Value(value = "${swagger.licenseUrl}")
	String licenseUrl;

	@Value(value = "${swagger.contact.name}")
	String contactName;

	@Value(value = "${swagger.contact.url}")
	String contactUrl;

	@Value(value = "${swagger.contact.email}")
	String contactEmail;

	@Bean
	public Docket api() {

		return new Docket(SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePath))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title(title);
		apiInfoBuilder.description(description);
		apiInfoBuilder.version(version);
		apiInfoBuilder.contact(new Contact(contactName, contactUrl, contactEmail));
		apiInfoBuilder.license(license);
		apiInfoBuilder.licenseUrl(licenseUrl);

		return apiInfoBuilder.build();
	}
}