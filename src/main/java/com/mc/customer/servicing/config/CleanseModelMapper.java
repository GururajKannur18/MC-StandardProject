package com.mc.customer.servicing.config;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.ModelMapper;

public class CleanseModelMapper extends ModelMapper
{

	public <D> D map(Object source, Class<D> destinationType, EntityCleanser cleanser) {
	  return map(cleanser.cleanse(source),destinationType);	
	}
	
	public <D> D map(Object sourceList, Type destinationType, EntityCleanser cleanser ) {
		
		if(sourceList instanceof Collection) {
			((Collection) sourceList).forEach(source -> source= cleanser.cleanse(source));
		}
		
		return map(sourceList, destinationType);
	}
}
