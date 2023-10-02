package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.Set;

import com.springboot.mondial.parsexml.constants.DBConstants;
import com.springboot.mondial.parsexml.utility.StringSetConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = DBConstants.DB_ISLAND_TABLE)
public class Island implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_ISLAND_COLUMN_ID)
	private String islandId;

	@Column(name = DBConstants.DB_ISLAND_COLUMN_NAME)
	private String islandName;

	@Column(name = DBConstants.DB_ISLAND_COLUMN_AREA)
	private String islandArea;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_ISLAND_COLUMN_COUNTRIES)
	private Set<String> islandCountry;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_ISLAND_COLUMN_PROVINCES)
	private Set<String> islandProvince;
}
