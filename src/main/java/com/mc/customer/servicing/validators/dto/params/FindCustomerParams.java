package com.mc.customer.servicing.validators.dto.params;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindCustomerParams {

	@Parameter(required = true, name="partialCustomerId", description = "Partial Customer Id")
	private String partialCustomerId;
	
	@Parameter(required = false, name="customerName", description = "Customer Name")
	private String customerName;
	
	@Parameter(required = false, name="ignoreStatus", schema = @Schema(type = "boolean", defaultValue = "false"))
	private boolean ignoreStatus;
	
	@Parameter(required = false, name="country", description = "Country codes")
	private String country;
	
}
