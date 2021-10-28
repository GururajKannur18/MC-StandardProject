package com.mc.customer.servicing.config;

public interface EntityCleanser<T> {

	T cleanse(T entity);
}
