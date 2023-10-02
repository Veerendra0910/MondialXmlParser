package com.springboot.mondial.parsexml.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class BaseResponse {
	private Boolean success;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorMessage;
}
