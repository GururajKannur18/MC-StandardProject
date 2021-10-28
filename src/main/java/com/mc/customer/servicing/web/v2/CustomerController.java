package com.mc.customer.servicing.web.v2;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.customer.servicing.constant.HeaderConstants;
import com.mc.customer.servicing.constant.VersionConstants;
import com.mc.customer.servicing.dto.CustomerDto;
import com.mc.customer.servicing.service.CustomerService;
import com.mc.customer.servicing.validators.dto.params.FindCustomerParams;
import com.mc.customer.servicing.validators.v1.FindCustomerParamsValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.media.MediaType;

@RestController(value = "CustomerControllerV2" )
@RequestMapping(path = "customers", produces = "application/json" )
@Validated
@Tag(name = "Customer", description = "Query at customer level")
public class CustomerController {

	private final FindCustomerParamsValidator findCustomerParamsValidator;
	private final CustomerService customerService;
	
	public static final String X_ACCEPT_VERSION_V2 = HeaderConstants.X_ACCEPT_VERSION + "=" +
														VersionConstants.API_VERSION_V2;

	public CustomerController(FindCustomerParamsValidator findCustomerParamsValidator,
			CustomerService customerService) {
		this.findCustomerParamsValidator = findCustomerParamsValidator;
		this.customerService = customerService;
	}
	
	@InitBinder("findCustomerParams")
	protected void initFindCustomerParamsBinder(WebDataBinder binder) {
		binder.addValidators(findCustomerParamsValidator);
	}
	
	@Operation(summary = "Find Customer")
	@Parameter(in = ParameterIn.HEADER, name = HeaderConstants.X_ACCEPT_VERSION, required = true, schema =  @Schema(defaultValue = VersionConstants.API_VERSION_V2))
	@Parameter(in = ParameterIn.QUERY, name= "size", description = "Number of records per page.", schema = @Schema(type = "int", defaultValue = "25"))
	@Parameter(in = ParameterIn.QUERY, name= "page", description = "Results page you want to retrive (0...N).", schema = @Schema(type = "int", defaultValue = "0"))
	@ApiResponse(responseCode = "200", description = "Successfully retrived customer")
	@ApiResponse(responseCode = "400", description = "Customer query invalid", content = @Content)
	@GetMapping(headers = X_ACCEPT_VERSION_V2)
	public Page<CustomerDto> findCustomers(@Valid @ParameterObject FindCustomerParams findCustomerParams,
			@Parameter(hidden = true) @NotNull Pageable pageable){
		
		return null; //;customerService.findCustomer(findCustomerParams,pageable);
	}
}
