package com.mc.customer.servicing.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mc.customer.servicing.utils.EnumUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiConstants {

	USER_ID("user_id"),
	PARAMETERS("parameters"),
	MOL_USER_ID("MOLUserId");
	
	@Getter
	String value;
	
	@JsonCreator
	public static ApiConstants create(String value) {
		return EnumUtil.caseInsensitiveConverstion(ApiConstants.class,value);
	}
}
