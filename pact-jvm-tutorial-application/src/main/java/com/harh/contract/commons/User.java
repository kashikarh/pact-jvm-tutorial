package com.harh.contract.commons;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class User {
	
	@NotBlank(message="User ID cannot be null or empty")
	private String userId;
	
	@NotBlank(message="User name cannot be null or empty")
	@JsonProperty(required=true)
	private String displayUserName;
	
	@NotBlank(message="User password cannot be null or empty")
	@JsonProperty(required=true)
	private String password;
	
	@JsonProperty("membershipCode")
	private String membershipCode = "SILVER";
}

