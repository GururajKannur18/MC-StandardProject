package com.mc.customer.servicing.client;

import java.net.URI;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public interface ApiClient {

	<R> R getForObject(URI uri, Class<R> rClass);
	
	<R> R getForObjectWithHeader(URI uri, Map<String,String> headers, Class<R> rClass);
	
	<R> R postForObject(URI uri, Object requestObject, Class<R> rClass);
	
	<R> R postForObjectWithHeader(URI uri, Object requestObject, Map<String,String> headers, Class<R> rClass);
	
	<R> R getForObject(URI uri, TypeReference<R> valueTypeRef);
}
