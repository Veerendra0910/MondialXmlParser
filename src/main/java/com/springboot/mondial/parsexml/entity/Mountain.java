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
@Table(name = DBConstants.DB_MOUNTAIN_TABLE)
public class Mountain implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_ID)
	private String mountainId;

	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_NAME)
	private String mountainName;

	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_LONGITUDE)
	private String mountainLongitude;

	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_LATITUDE)
	private String mountainLatitude;

	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_HEIGHT)
	private String mountainHeight;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_COUNTRIES)
	private Set<String> mountainCountries;

	@Convert(converter = StringSetConverter.class)
	@Column(name = DBConstants.DB_MOUNTAIN_COLUMN_PROVINCES)
	private Set<String> mountainProvinces;
}
