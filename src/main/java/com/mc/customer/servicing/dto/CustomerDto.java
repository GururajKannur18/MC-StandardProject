package com.mc.customer.servicing.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Customer")
public class CustomerDto {

	private String id;
	private String firstName;
	private String phone;
	private String phone_extn_number;
	private String status;
}
