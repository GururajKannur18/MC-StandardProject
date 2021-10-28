package com.mc.customer.servicing.validators.v1;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mc.customer.servicing.validators.dto.params.FindCustomerParams;

@Component
public class FindCustomerParamsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FindCustomerParams.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		FindCustomerParams findCustomerParams = (FindCustomerParams) target;
		
		if(StringUtils.isEmpty(findCustomerParams.getCustomerName()) && StringUtils.isEmpty(findCustomerParams.getPartialCustomerId())) {
			errors.reject("empty.search.param");
		}
		
	}

}
