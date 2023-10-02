package com.springboot.mondial.parsexml.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.springboot.mondial.parsexml.constants.DBConstants;
import com.springboot.mondial.parsexml.dto.CountryParameter;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = DBConstants.DB_COUNTRY_TABLE)
@Getter
@Setter
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = DBConstants.DB_COUNTRY_COLUMN_ID)
	private String countryId;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_NAME)
	private String countryName;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_CAPITAL)
	private String countryCapital;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_POPULATION)
	private String countryPopulation;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_DATA_CODE)
	private String countryDataCode;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_TOTAL_AREA)
	private String countryTotalArea;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_POPULATION_GROWTH)
	private String countryPopulationGrowth;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_INFANT_MORTALITY)
	private String countryInfantMortality;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_GDP_AGRI)
	private String countryGdpAgri;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_GDP_TOTAL)
	private String countryGdpTotal;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_INFLATION)
	private String countryInflation;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_INDEEP_DATE)
	private String countryIndeepDate;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_GOVERNMENT)
	private String countryGovernment;

	@Column(name = DBConstants.DB_COUNTRY_COLUMN_CAR_CODE)
	private String countryCarCode;

	@OneToMany(mappedBy = DBConstants.DB_COUNTRY_COLUMN_MAPPEDBY, cascade = CascadeType.ALL)
	private Set<Province> provinces;

	@OneToMany(mappedBy = DBConstants.DB_COUNTRY_COLUMN_MAPPEDBY, cascade = CascadeType.ALL)
	private Set<City> cities;

	@ElementCollection
	@CollectionTable(name = DBConstants.DB_COUNTRY_COLLETIONTABLE_COUTRY_PARAMETERS, joinColumns = @JoinColumn(name = DBConstants.DB_COUNTRY_COLUMN_ID))
	@AttributeOverrides({ @AttributeOverride(name = "ethnicsGroupName", column = @Column(name = DBConstants.DB_COUNTRY_ETHNICS_GROUPS_NAME)),
						  @AttributeOverride(name = "ethnicsGroupPercentage", column = @Column(name = DBConstants.DB_COUNTRY_ETHNICS_GROUPS_PERCENTAGE)),
						  @AttributeOverride(name = "religionsName", column = @Column(name = DBConstants.DB_COUNTRY_RELIGIONS_NAME)),
						  @AttributeOverride(name = "religionsPercentage", column = @Column(name = DBConstants.DB_COUNTRY_RELIGIONS_PERCENTAGE)),
						  @AttributeOverride(name = "encompassedContinent", column = @Column(name = DBConstants.DB_COUNTRY_ENCOMPASSED_CONTINENT)),
						  @AttributeOverride(name = "encompassedPercentage", column = @Column(name = DBConstants.DB_COUNTRY_ENCOMPASSED_PERCENTAGE)),
						  @AttributeOverride(name = "borderCountry", column = @Column(name = DBConstants.DB_COUNTRY_BORDER_COUNTRY)),
						  @AttributeOverride(name = "borderLength", column = @Column(name = DBConstants.DB_COUNTRY_BORDER_LENGTH))})
	
	private Set<CountryParameter> countryParameters;

	public void addProvince(Province province) {
		if (province != null) {
			if (provinces == null) {
				provinces = new HashSet<>();
			}
			province.setCountry(this);
			provinces.add(province);
		}
	}

	public void addCity(City city) {
		if (city != null) {
			if (cities == null) {
				cities = new HashSet<>();
			}
			city.setCountry(this);
			cities.add(city);
		}
	}

}
