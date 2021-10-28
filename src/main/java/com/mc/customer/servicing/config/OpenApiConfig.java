package com.mc.customer.servicing.config;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;

import com.mc.customer.servicing.constant.AppConstants;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;

@Configuration
public class OpenApiConfig {

	/**
	 * Method to create and retun OpeApi bean
	 * 
	 * @return OpenApi
	 */

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info().title("Customer Servicing")
				.description("A view of list of all Customer" + "servicing API").termsOfService("https://www.test.com")
				.contact(new Contact().email("gururaj_kannur@live.in").name("API Support")).version("1.0"));
	}
	
	/**
	 * Method to create api doc for V1 version
	 * 
	 * @return {@link GroupedOpenApi}
	 */
	@Bean
	public GroupedOpenApi v1OpenApi() {
		String[] packageToScan = {"com.mc.customer.servicing.web.api.v1"};
		return GroupedOpenApi.builder().setGroup(AppConstants.GROUP_V1).packagesToScan(packageToScan).build();
	}
	
	@Bean
	public GroupedOpenApi v2OpenApi() {
		String[] packageToScan = {"com.mc.customer.servicing.web.api.v2"};
		return GroupedOpenApi.builder().setGroup(AppConstants.GROUP_V2).packagesToScan(packageToScan).build();
	}
	
	/**
	 *  Create v3OpenAPI Bean as required as per above
	 */
	
	/**
	 * Method for sorting Tag names
	 * @return {@link OpenApiCustomiser}
	 */
	
	@Bean
	public OpenApiCustomiser sortTagsAplhabetically() {
		
		return openApi-> openApi.setTags(
				openApi.getTags().stream().sorted(Comparator.comparing(tag -> StringUtils.stripAccents(tag.getName())))
				.collect(Collectors.toList()));
	}
	
	/**
	 *  Soring parameters alphabetically
	 *  @return {@link OperationCustomizer}
	 */
	
	@Bean
	public OperationCustomizer operationCustomizer() {
		return (Operation operation, HandlerMethod handMethod ) -> {
			List<Parameter> parameterList = operation.getParameters();
			 if(!CollectionUtils.isEmpty(parameterList))
				 operation.setParameters(parameterList.stream().sorted(byParameterName()).collect(Collectors.toList()));
				 return operation;
		};
	}

	private Comparator<Parameter> byParameterName() {
		return Comparator.comparing(Parameter::getName);
	}
}