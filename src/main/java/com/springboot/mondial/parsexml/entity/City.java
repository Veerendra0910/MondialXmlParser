package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.springboot.mondial.parsexml.constants.DBConstants;
import com.springboot.mondial.parsexml.utility.StringSetConverter;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = DBConstants.DB_CITY_TABLE)
@Data
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_CITY_COLUMN_ID)
	private String cityId;
	
	@Convert(converter = StringSetConverter.class)
	@Column(name=DBConstants.DB_CITY_COLUMN_NAMES)
	private Set<String> cityNames;
	
	@Column(name = DBConstants.DB_CITY_COLUMN_LONGITUDE)
	private String cityLongitude;

	@Column(name = DBConstants.DB_CITY_COLUMN_LATITUDE)
	private String cityLatitude;
	
	@ElementCollection
	@CollectionTable(name=DBConstants.DB_CITY_COLLECTIONTABLE_POPULATION, joinColumns = @JoinColumn(name=DBConstants.DB_CITY_COLUMN_ID))
	@MapKeyColumn(name=DBConstants.DB_CITY_COLUMN_POPULATION_YEAR)
	@Column(name=DBConstants.DB_CITY_COLUMN_POPULATION)
	private Map<String, String> cityPopulationMap;
	
	@ElementCollection
	@CollectionTable(name=DBConstants.DB_CITY_COLLECTIONTABLE_LOCATION, joinColumns = @JoinColumn(name=DBConstants.DB_CITY_COLUMN_ID))
	@MapKeyColumn(name=DBConstants.DB_CITY_COLUMN_LOCATION_TYPE)
	@Column(name=DBConstants.DB_CITY_COLUMN_LOCATION_WATER)
	private Map<String, String> cityLocationMap;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name=DBConstants.DB_CITY_COUNTRY_JOIN_COLUMN)
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name=DBConstants.DB_CITY_PROVINCE_JOIN_COLUMN)
	private Province province;
}
