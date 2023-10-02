package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MondialEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Continent> continents;
	private List<Country> countries;
	private List<Province> provinces;
	private List<Organization> organizations;
	private List<Mountain> mountains;
	private List<Desert> deserts;
	private List<Island> islands;
	private List<River> rivers;
	private List<Sea> seas;
	private List<Lake> lakes;

}
