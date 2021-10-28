package com.mc.customer.servicing.config;

import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Autowired(required = false)
	List<PropertyMap> propertyMapList;

	@Bean
	public CleanseModelMapper modelMapper() {
		CleanseModelMapper modelMapper = new CleanseModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		
		if(propertyMapList!=null) {
			propertyMapList.forEach(modelMapper::addMappings);
		}
		
		//modelMapper().addConverter(converter);
		
		return modelMapper;
	}
}
