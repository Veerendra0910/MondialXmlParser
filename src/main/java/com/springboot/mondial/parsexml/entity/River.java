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
@Table(name = DBConstants.DB_RIVER_TABLE)
public class River implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_RIVER_COLUMN_ID)
	private String riverId;

	@Column(name = DBConstants.DB_RIVER_COLUMN_NAME)
	private String riverName;

	@Column(name = DBConstants.DB_RIVER_COLUMN_LENGTH)
	private String riverLength;

	@Column(name = DBConstants.DB_RIVER_COLUMN_TYPE)
	private String riverType;

	@Column(name = DBConstants.DB_RIVER_COLUMN_WATER)
	private String riverWater;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_RIVER_COLUMN_COUNTRIES)
	private Set<String> riverCountry;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_RIVER_COLUMN_PROVINCES)
	private Set<String> riverProvince;
}
