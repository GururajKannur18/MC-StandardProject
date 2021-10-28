package com.mc.customer.servicing.utils;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class EnumUtil {

	private EnumUtil() {}

	@SuppressWarnings("deprecation")
	public static <T extends Enum<T>> T caseInsensitiveConverstion(Class<T> clazz, String stringValue) {
		
		try {
			return T.valueOf(clazz, stringValue.toUpperCase());
		} catch(Exception e) {
			throw new HttpMessageNotReadableException("", new InvalidFormatException(null, "", stringValue, clazz));
		}
		
	}
	
}
