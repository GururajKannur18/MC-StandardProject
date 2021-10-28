package com.mc.customer.servicing.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mc.customer.servicing.config.CleanseModelMapper;
import com.mc.customer.servicing.constant.AppConstants;
import com.mc.customer.servicing.dto.CustomerDto;
import com.mc.customer.servicing.entity.Customer;
import com.mc.customer.servicing.exception.v1.NotFoundException;
import com.mc.customer.servicing.repository.CustomerRepository;
import com.mc.customer.servicing.validators.dto.params.FindCustomerParams;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	private CustomerRepository customerRepository;
	private CleanseModelMapper modelMapper;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, CleanseModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	public Page<CustomerDto> findCustomer(@Valid FindCustomerParams findCustomerParams, @NotNull Pageable pageable) {
			
		Sort sort = Sort.by(Sort.Direction.ASC,"name");
		pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
		@SuppressWarnings("unchecked")
		
		List<Customer> customerPage = null; //customerRepository.findAll();
		
		if(null == customerPage)
			throw new NotFoundException(AppConstants.CS_APP);
		
		@SuppressWarnings("serial")
		List<CustomerDto> customerDtoList = modelMapper.map(customerPage, new TypeToken<List<CustomerDto>>(){}.getType());
		return new PageImpl<CustomerDto>(customerDtoList,pageable,customerPage.size());
		
	}

}
