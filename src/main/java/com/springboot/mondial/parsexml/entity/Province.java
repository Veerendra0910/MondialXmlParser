package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.springboot.mondial.parsexml.constants.DBConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DBConstants.DB_PROVINCE_TABLE)
@Getter
@Setter
public class Province implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_PROVINCE_COLUMN_ID)
	private String provinceId;
	
	@Column(name = DBConstants.DB_PROVINCE_COLUMN_NAME)
	private String provinceName;

	@Column(name = DBConstants.DB_PROVINCE_COLUMN_CAPITAL)
	private String provinceCapital;

	@Column(name = DBConstants.DB_PROVINCE_COLUMN_POPULATION)
	private String provicePopulation;

	@Column(name = DBConstants.DB_PROVINCE_COLUMN_AREA)
	private String provinceArea;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name=DBConstants.DB_PROVINCE_COUNTRY_JOIN_COLUMN)
	private Country country;
	
	@OneToMany(mappedBy = "province", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	private Set<City> cities;
	
	public void addCity(City city) {
		if (city != null) {
			if (cities == null) {
				cities = new HashSet<>();
			}
			city.setProvince(this);
			cities.add(city);
		}
	}

	
}
