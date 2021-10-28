package com.mc.customer.servicing.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.mc.customer.servicing.dto.CustomerDto;
import com.mc.customer.servicing.entity.Customer;

@Component
public class CustomerToCustomerDtoMapping extends PropertyMap<Customer, CustomerDto> {

	@Override
	protected void configure() {
		map().setFirstName(source.getName());
		map().setPhone_extn_number(source.getPhone_extn());
	}
}
