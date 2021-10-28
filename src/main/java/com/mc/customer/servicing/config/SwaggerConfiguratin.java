package com.mc.customer.servicing.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguratin {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select()
				 .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(RequestHandlerSelectors.basePackage("com.mc.customer.servicing.web.v1")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo("v1")).useDefaultResponseMessages(false).ignoredParameterTypes(Pageable.class)
				.tags(new Tag("Customer", "Customer Servicing"), new Tag("message", null)).globalResponseMessage(
						RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500).message("").build()));
	}

	private ApiInfo apiInfo(String version) {
		return new ApiInfoBuilder().title("Customer Servicing APIs ")
				.description("A view of all Customer Servicing Apis").version(version).build();
	}
	
	@Bean
	public Docket V2Api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("v2").select()
				 .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(RequestHandlerSelectors.basePackage("com.mc.customer.servicing.web.v2")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo("v2")).useDefaultResponseMessages(false).ignoredParameterTypes(Pageable.class)
				.tags(new Tag("Extract", "Bulk Operations"));
	}
	//Control the display of the request duration(in milliseconds)
	@Bean
		UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().displayRequestDuration(true).build();
	}
	
	   @Bean
	    public LinkDiscoverers discoverers() {
	        List<LinkDiscoverer> plugins = new ArrayList<>();
	        plugins.add(new CollectionJsonLinkDiscoverer());
	        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

	    }
}
