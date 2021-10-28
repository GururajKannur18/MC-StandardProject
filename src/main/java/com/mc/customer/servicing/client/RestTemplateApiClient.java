package com.mc.customer.servicing.client;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.customer.servicing.constant.ApiConstants;
import com.mc.customer.servicing.constant.ErrorConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTemplateApiClient implements ApiClient {

	private final String baseUrl;
	private RestTemplate restTemplate;
	private ObjectMapper mapper;

	public RestTemplateApiClient(String baseUrl, RestTemplate restTemplate) {
		super();
		this.baseUrl = baseUrl;
		this.restTemplate = restTemplate;
	}

	/**
	 * @param baseUrl : baseURI of the Service
	 * @param mapper: object mapper to map response of the service with secified
	 *                class
	 */
	public RestTemplateApiClient(String baseUrl, RestTemplate restTemplate, ObjectMapper mapper) {
		super();
		this.baseUrl = baseUrl;
		this.restTemplate = restTemplate;
		this.mapper = mapper;
	}

	@Override
	public <R> R getForObject(URI uri, Class<R> rClass) {
		URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl).path(uri.getPath()).query(uri.getQuery()).build()
				.toUri();
		return restTemplate.getForObject(builder, rClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> R getForObjectWithHeader(URI uri, Map<String, String> headers, Class<R> rClass) {
		URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl).path(uri.getPath()).query(uri.getQuery()).build()
				.toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		if (httpHeaders.containsKey(ApiConstants.USER_ID.getValue())) {
			String value = headers.get(ApiConstants.USER_ID.getValue());
			headers.remove(ApiConstants.USER_ID.getValue());
			headers.put(ApiConstants.MOL_USER_ID.getValue(), value);
		}

		for (Map.Entry<String, String> header : headers.entrySet()) {
			httpHeaders.add(header.getKey(), header.getValue());
		}

		HttpEntity<String> entity = new HttpEntity<>(ApiConstants.PARAMETERS.getValue(), httpHeaders);
		HttpEntity<?> response = restTemplate.exchange(builder, HttpMethod.GET, entity, rClass);
		return (R) response.getBody();
	}

	@Override
	public <R> R postForObject(URI uri, Object requestObject, Class<R> rClass) {
		URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl).path(uri.getPath()).query(uri.getQuery()).build()
				.toUri();
		return restTemplate.postForObject(builder, requestObject, rClass);
	}

	@Override
	public <R> R postForObjectWithHeader(URI uri, Object requestObject, Map<String, String> headers, Class<R> rClass) {
		URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl).path(uri.getPath()).query(uri.getQuery()).build()
				.toUri();
		HttpHeaders httpHeaders = new HttpHeaders();
		for (Map.Entry<String, String> header : headers.entrySet()) {
			httpHeaders.add(header.getKey(), header.getValue());
		}
		HttpEntity<Object> entity = new HttpEntity<Object>(requestObject, httpHeaders);
		return restTemplate.postForObject(builder, entity, rClass);
	}

	@Override
	public <R> R getForObject(URI uri, TypeReference<R> valueTypeRef) {
		URI builder = UriComponentsBuilder.fromHttpUrl(baseUrl).path(uri.getPath()).query(uri.getQuery()).build().toUri();		
		String response= restTemplate.getForObject(builder, String.class);
		try {
			return mapper.readValue(response, valueTypeRef);
		}catch (IOException e) {
			log.error(ErrorConstants.REST_ERROR_TEMPLATE, e.getMessage());
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
