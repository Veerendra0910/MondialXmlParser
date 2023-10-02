package com.springboot.mondial.parsexml.dto;

import lombok.Data;

@Data
public class CountryParameter {
	private String ethnicsGroupName;
	private String ethnicsGroupPercentage;
	private String religionsName;
	private String religionsPercentage;
	private String encompassedContinent;
	private String encompassedPercentage;
	private String borderCountry;
	private String borderLength;
}
