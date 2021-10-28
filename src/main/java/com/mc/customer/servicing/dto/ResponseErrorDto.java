package com.mc.customer.servicing.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "ResponseError")
public class ResponseErrorDto {

	private Integer code;
	private List<String> errorMessage;
}
