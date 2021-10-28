package com.mc.customer.servicing.utils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class ObjectMapperUtlis {

	/**
	 * Hide from public usage
	 */
	private ObjectMapperUtlis() {
	}

	/**
	 * <p>
	 * Note: outClass object must have default constructor with no arguments
	 * 
	 * @param <D>      type of result object
	 * @param <T>      type of source object to map from
	 * @param entity   entity that needs to be mapped
	 * @param outClass class of the result
	 * @param mapper
	 * @return new object of <code>outClass</code> type.
	 */

	public static <D, T> D map(final T entity, Class<D> outClass, ModelMapper mapper) {
		return mapper.map(entity, outClass);
	}

	/**
	 * <p>
	 * Note: outClass object must have default constructor with no arguments
	 * 
	 * @param <D>        type of object in the result set
	 * @param <T>        type of entity in <code> entityList </code>
	 * @param entityList List of entities that needs to be mapped
	 * @param outClass   class of result list element
	 * @param mapper
	 * @return list of mapped object with <code> D </code> type.
	 */
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass, ModelMapper mapper) {
		return entityList.stream().map(entity -> map(entity, outClass, mapper)).collect(Collectors.toList());
	}

	/**
	 * 
	 * @param <S>
	 * @param <D>
	 * @param source      object to map from
	 * @param destination object to map to
	 * @param mapper
	 * @return
	 */
	public static <S, D> D map(final S source, D destination, ModelMapper mapper) {
		mapper.map(source, destination);
		return destination;
	}
}
