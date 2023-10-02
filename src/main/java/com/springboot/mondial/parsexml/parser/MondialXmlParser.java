package com.springboot.mondial.parsexml.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.springboot.mondial.parsexml.constants.XmlConstants;
import com.springboot.mondial.parsexml.dto.CountryParameter;
import com.springboot.mondial.parsexml.entity.City;
import com.springboot.mondial.parsexml.entity.Continent;
import com.springboot.mondial.parsexml.entity.Country;
import com.springboot.mondial.parsexml.entity.Desert;
import com.springboot.mondial.parsexml.entity.Island;
import com.springboot.mondial.parsexml.entity.Lake;
import com.springboot.mondial.parsexml.entity.MondialEntity;
import com.springboot.mondial.parsexml.entity.Mountain;
import com.springboot.mondial.parsexml.entity.Organization;
import com.springboot.mondial.parsexml.entity.Province;
import com.springboot.mondial.parsexml.entity.River;
import com.springboot.mondial.parsexml.entity.Sea;
import com.springboot.mondial.parsexml.utility.CustomUtility;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MondialXmlParser {

	public static final String TRACE_TAG = "MondialXmlParser.";

	public MondialEntity parserXml(String xmlFilePath) throws XMLStreamException {

		log.info(TRACE_TAG + " method parserXml() Starts ");

		MondialEntity mondialEntity = null;

		Continent continent = null;
		List<Continent> continents = new ArrayList<>();

		Country country = null;
		List<Country> countries = new ArrayList<>();
		CountryParameter countryParameter = null;
		Set<CountryParameter> countryParameters = new LinkedHashSet<>();

		Province province = null;
		List<Province> provinces = new ArrayList<>();

		City city = null;
		boolean isCityName = Boolean.FALSE;
		Set<String> cityNames = new LinkedHashSet<>();
		Map<String, String> cityPopulationMap = new HashMap<>();
		Map<String, String> cityLocationMap = new HashMap<>();

		Organization organization = null;
		List<Organization> organizations = new ArrayList<>();
		Map<String, String> organizationMemberMap = new HashMap<>();

		boolean isMountainLocated = Boolean.FALSE;
		Mountain mountain = null;
		List<Mountain> mountains = new ArrayList<>();
		Set<String> mountainCountries = new LinkedHashSet<>();
		Set<String> mountainProvinces = new LinkedHashSet<>();

		boolean isDesertLocated = Boolean.FALSE;
		Desert desert = null;
		List<Desert> deserts = new ArrayList<>();
		Set<String> desertCountries = new LinkedHashSet<>();
		Set<String> desertProvinces = new LinkedHashSet<>();

		boolean isIslandLocated = Boolean.FALSE;
		Island island = null;
		List<Island> islands = new ArrayList<>();
		Set<String> islandCountries = new LinkedHashSet<>();
		Set<String> islandProvinces = new LinkedHashSet<>();

		boolean isRiverLocated = Boolean.FALSE;
		River river = null;
		List<River> rivers = new ArrayList<>();
		Set<String> riverCountries = new LinkedHashSet<>();
		Set<String> riverProvinces = new LinkedHashSet<>();

		boolean isSeaLocated = Boolean.FALSE;
		Sea sea = null;
		List<Sea> seas = new ArrayList<>();
		Set<String> seaCountries = new LinkedHashSet<>();
		Set<String> seaProvinces = new LinkedHashSet<>();

		boolean isLakeLocated = Boolean.FALSE;
		Lake lake = null;
		List<Lake> lakes = new ArrayList<>();
		Set<String> lakeCountries = new LinkedHashSet<>();
		Set<String> lakeProvinces = new LinkedHashSet<>();
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = null;

		try {
			xmlEventReader = xmlInputFactory.createXMLEventReader(new ClassPathResource(xmlFilePath).getInputStream());
			while (xmlEventReader.hasNext()) {
				XMLEvent xmlEvent = xmlEventReader.nextEvent();

				if (xmlEvent.isStartElement()) {
					StartElement startElement = xmlEvent.asStartElement();

					switch (startElement.getName().getLocalPart()) {

					case XmlConstants.MONDIAL:
						mondialEntity = new MondialEntity();
						break;

					case XmlConstants.CONTINENT:
						continent = new Continent();
						continent = this.setContinentValues(startElement, continent);
						break;

					case XmlConstants.COUNTRY:
						country = new Country();
						countryParameter = new CountryParameter();
						isCityName = Boolean.FALSE;
						country = this.setCountryAttributes(startElement, country);
						break;

					case XmlConstants.PROVINCE:
						province = new Province();
						province = this.setProvinces(startElement, province);
						break;

					case XmlConstants.CITY:
						city = new City();
						isCityName = Boolean.TRUE;
						city = this.setCities(startElement, city);
						break;

					case XmlConstants.CITY_NAME:
						if (isCityName) {
							xmlEvent = xmlEventReader.nextEvent();
							cityNames.add(!xmlEvent.asCharacters().getData().isBlank()
									? xmlEvent.asCharacters().getData().strip()
									: "");
							// Set City Names
							city.setCityNames(cityNames);
						}
						break;

					case XmlConstants.CITY_POPULATION:
						xmlEvent = xmlEventReader.nextEvent();
		
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.CITY_POPULATION_YEAR))))
							// Set Map with Population Year as Key and Population as Value
							cityPopulationMap
									.put(startElement.getAttributeByName(new QName(XmlConstants.CITY_POPULATION_YEAR))
											.getValue().strip(), xmlEvent.asCharacters().getData().strip());

						city.setCityPopulationMap(cityPopulationMap);
						break;

					case XmlConstants.CITY_LOCATED_AT:
						xmlEvent = xmlEventReader.nextEvent();
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.CITY_LOCATED_AT_TYPE)))
								&& CustomUtility.isObjectNotNull(
										startElement.getAttributeByName(new QName(XmlConstants.CITY_LOCATED_AT_WATER))))

							cityLocationMap.put(
									startElement.getAttributeByName(new QName(XmlConstants.CITY_LOCATED_AT_TYPE))
											.getValue().strip(),
									startElement.getAttributeByName(new QName(XmlConstants.CITY_LOCATED_AT_WATER))
											.getValue().strip());

						city.setCityLocationMap(cityLocationMap);
						break;

					case XmlConstants.COUNTRY_ETHNICSGROUPS:
						xmlEvent = xmlEventReader.nextEvent();

						if (CustomUtility.isObjectNotNull(startElement
								.getAttributeByName(new QName(XmlConstants.COUNTRY_ETHNICSGROUPS_PERCENTAGE))))
							countryParameter.setEthnicsGroupPercentage(startElement
									.getAttributeByName(new QName(XmlConstants.COUNTRY_ETHNICSGROUPS_PERCENTAGE))
									.getValue().strip());

						if (CustomUtility.isStringNotNull(xmlEvent.asCharacters().toString().strip()))
							countryParameter.setEthnicsGroupName(xmlEvent.asCharacters().toString().strip());

						break;

					case XmlConstants.COUNTRY_RELIGIONS:
						xmlEvent = xmlEventReader.nextEvent();
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_RELIGIONS_PERCENTAGE))))
							countryParameter.setReligionsPercentage(startElement
									.getAttributeByName(new QName(XmlConstants.COUNTRY_RELIGIONS_PERCENTAGE)).getValue()
									.strip());

						if (CustomUtility.isStringNotNull(xmlEvent.asCharacters().toString().strip()))
							countryParameter.setReligionsName(xmlEvent.asCharacters().toString().strip());

						break;

					case XmlConstants.COUNTRY_ENCOMPASSED:
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_ENCOMPASSED_CONTINENT))))
							countryParameter.setEncompassedContinent(startElement
									.getAttributeByName(new QName(XmlConstants.COUNTRY_ENCOMPASSED_CONTINENT))
									.getValue().strip());

						if (CustomUtility.isObjectNotNull(startElement
								.getAttributeByName(new QName(XmlConstants.COUNTRY_ENCOMPASSED_PERCENTAGE))))
							countryParameter.setEncompassedPercentage(startElement
									.getAttributeByName(new QName(XmlConstants.COUNTRY_ENCOMPASSED_PERCENTAGE))
									.getValue().strip());

						break;

					case XmlConstants.COUNTRY_BORDER:
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_BORDER_LENGTH))))
							countryParameter.setBorderLength(
									startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_BORDER_LENGTH))
											.getValue().strip());

						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_BORDER_COUNTRY))))
							countryParameter.setBorderCountry(
									startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_BORDER_COUNTRY))
											.getValue().strip());
						break;

					case XmlConstants.ORGANIZATION:
						organization = new Organization();
						organization = this.setOrganizationValues(startElement, organization);
						break;

					case XmlConstants.ORGANIZATION_MEMBERS:
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_MEMBERS_TYPE)))
								&& CustomUtility.isObjectNotNull(startElement
										.getAttributeByName(new QName(XmlConstants.ORGANIZATION_MEMBERS_COUNTRY))))

							organizationMemberMap.put(
									startElement
											.getAttributeByName(new QName(XmlConstants.ORGANIZATION_MEMBERS_COUNTRY))
											.getValue().strip(),
									startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_MEMBERS_TYPE))
											.getValue().strip());

						organization.setOrganizationMemberMap(organizationMemberMap);

						break;

					case XmlConstants.MOUNTAIN:
						mountain = new Mountain();
						isMountainLocated = Boolean.TRUE;
						mountain = this.setMountainValues(startElement, mountain);
						break;

					case XmlConstants.DESERT:
						desert = new Desert();
						isDesertLocated = Boolean.TRUE;
						// Making Previous Located Fields to False
						isMountainLocated = Boolean.FALSE;
						desert = this.setDesertValues(startElement, desert);
						break;

					case XmlConstants.ISLAND:
						island = new Island();
						isIslandLocated = Boolean.TRUE;
						// Making Previous Located Fields to False
						isMountainLocated = Boolean.FALSE;
						isDesertLocated = Boolean.FALSE;
						island = this.setIslandValues(startElement, island);
						break;

					case XmlConstants.RIVER:
						river = new River();
						isRiverLocated = Boolean.TRUE;
						// Making Previous Located Fields to False
						isMountainLocated = Boolean.FALSE;
						isDesertLocated = Boolean.FALSE;
						isIslandLocated = Boolean.FALSE;
						river = this.setRiverValues(startElement, river);

					case XmlConstants.RIVER_TO:
						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.RIVER_TO_TYPE))))
							river.setRiverType(startElement.getAttributeByName(new QName(XmlConstants.RIVER_TO_TYPE))
									.getValue().strip());

						if (CustomUtility.isObjectNotNull(
								startElement.getAttributeByName(new QName(XmlConstants.RIVER_TO_WATER))))
							river.setRiverWater(startElement.getAttributeByName(new QName(XmlConstants.RIVER_TO_WATER))
									.getValue().strip());
						break;

					case XmlConstants.SEA:
						sea = new Sea();
						isSeaLocated = Boolean.TRUE;
						// Making Previous Located Fields to False
						isMountainLocated = Boolean.FALSE;
						isDesertLocated = Boolean.FALSE;
						isIslandLocated = Boolean.FALSE;
						isRiverLocated = Boolean.FALSE;
						sea = this.setSeaValues(startElement, sea);
						break;

					case XmlConstants.LAKE:
						lake = new Lake();
						isLakeLocated = Boolean.TRUE;
						// Making Previous Located Fields to False
						isMountainLocated = Boolean.FALSE;
						isDesertLocated = Boolean.FALSE;
						isIslandLocated = Boolean.FALSE;
						isRiverLocated = Boolean.FALSE;
						isSeaLocated = Boolean.FALSE;
						lake = this.setLakeValues(startElement, lake);
						break;

					case XmlConstants.LOCATED:
						if (isMountainLocated && CustomUtility.isObjectNotNull(mountain)) {

							mountainCountries.add(this.getLocatedCountry(startElement));
							mountainProvinces.add(this.getLocatedProvince(startElement));

							mountain.setMountainCountries(mountainCountries);
							mountain.setMountainProvinces(mountainProvinces);
						}

						if (isDesertLocated && CustomUtility.isObjectNotNull(desert)) {

							desertCountries.add(this.getLocatedCountry(startElement));
							desertProvinces.add(this.getLocatedProvince(startElement));

							desert.setDesertCountry(desertCountries);
							desert.setDesertProvince(desertProvinces);
						}

						if (isIslandLocated && CustomUtility.isObjectNotNull(island)) {

							islandCountries.add(this.getLocatedCountry(startElement));
							islandProvinces.add(this.getLocatedProvince(startElement));

							island.setIslandCountry(islandCountries);
							island.setIslandProvince(islandProvinces);
						}

						if (isRiverLocated && CustomUtility.isObjectNotNull(river)) {

							riverCountries.add(this.getLocatedCountry(startElement));
							riverProvinces.add(this.getLocatedProvince(startElement));

							river.setRiverCountry(riverCountries);
							river.setRiverProvince(riverProvinces);

						}

						if (isSeaLocated && CustomUtility.isObjectNotNull(sea)) {

							seaCountries.add(this.getLocatedCountry(startElement));
							seaProvinces.add(this.getLocatedProvince(startElement));

							sea.setSeaCountry(seaCountries);
							sea.setSeaProvince(seaProvinces);
						}

						if (isLakeLocated && CustomUtility.isObjectNotNull(lake)) {

							lakeCountries.add(this.getLocatedCountry(startElement));
							lakeProvinces.add(this.getLocatedProvince(startElement));

							lake.setLakeCountry(lakeCountries);
							lake.setLakeProvince(lakeProvinces);

						}
						break;
					}
				}
				if (xmlEvent.isEndElement()) {
					EndElement endElement = xmlEvent.asEndElement();

					switch (endElement.getName().getLocalPart()) {

					case XmlConstants.CONTINENT:
						continents.add(continent);
						break;

					case XmlConstants.PROVINCE:
						province.addCity(city);
						break;

					case XmlConstants.COUNTRY:
						countryParameters.add(countryParameter);
						country.setCountryParameters(countryParameters);
						country.addCity(city);
						country.addProvince(province);
						countries.add(country);
						break;

					case XmlConstants.ORGANIZATION:
						organizations.add(organization);
						break;

					case XmlConstants.MOUNTAIN:
						mountains.add(mountain);
						break;

					case XmlConstants.DESERT:
						deserts.add(desert);
						break;

					case XmlConstants.ISLAND:
						islands.add(island);
						break;

					case XmlConstants.RIVER:
						rivers.add(river);
						break;

					case XmlConstants.SEA:
						seas.add(sea);
						break;

					case XmlConstants.LAKE:
						lakes.add(lake);
						break;

					case XmlConstants.MONDIAL:
						mondialEntity.setContinents(continents);
						mondialEntity.setCountries(countries);
						mondialEntity.setProvinces(provinces);
						mondialEntity.setOrganizations(organizations);
						mondialEntity.setMountains(mountains);
						mondialEntity.setDeserts(deserts);
						mondialEntity.setIslands(islands);
						mondialEntity.setRivers(rivers);
						mondialEntity.setSeas(seas);
						mondialEntity.setLakes(lakes);
						break;
					}
				}
			}
		} catch (XMLStreamException | IOException e) {
			log.error(TRACE_TAG + " Exception at parserXml() " + e.getMessage());
			e.printStackTrace();
		} finally {
			// xmlEventReader.close();
			log.info(TRACE_TAG + " method parserXml() Ends ");
		}
		return mondialEntity;
	}

	// Set Continent Attribute Values
	private Continent setContinentValues(StartElement startElement, Continent continent) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.CONTINENT_ID))))
			continent.setContinentId(
					startElement.getAttributeByName(new QName(XmlConstants.CONTINENT_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.CONTINENT_NAME))))
			continent.setContinentName(
					startElement.getAttributeByName(new QName(XmlConstants.CONTINENT_NAME)).getValue().strip());

		return continent;
	}

	// Set Country Attribute Values
	private Country setCountryAttributes(StartElement startElement, Country country) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_ID)))) {
			country.setCountryId(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_ID)).getValue().strip());
		}

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_NAME))))
			country.setCountryName(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_CAPITAL))))
			country.setCountryCapital(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_CAPITAL)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_POPULATION))))
			country.setCountryPopulation(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_POPULATION)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_DATACODE))))
			country.setCountryDataCode(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_DATACODE)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_TOTAL_AREA))))
			country.setCountryTotalArea(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_TOTAL_AREA)).getValue().strip());

		if (CustomUtility
				.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_POPULATION_GROWTH))))
			country.setCountryPopulationGrowth(startElement
					.getAttributeByName(new QName(XmlConstants.COUNTRY_POPULATION_GROWTH)).getValue().strip());

		if (CustomUtility
				.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_INFANT_MORTALITY))))
			country.setCountryInfantMortality(startElement
					.getAttributeByName(new QName(XmlConstants.COUNTRY_INFANT_MORTALITY)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GDP_AGRI))))
			country.setCountryGdpAgri(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GDP_AGRI)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GDP_TOTAL))))
			country.setCountryGdpTotal(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GDP_TOTAL)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_INFLATION))))
			country.setCountryInflation(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_INFLATION)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_INDEEP_DATE))))
			country.setCountryIndeepDate(CustomUtility.formatDate(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_INDEEP_DATE)).getValue().strip()));

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GOVERNMENT))))
			country.setCountryGovernment(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_GOVERNMENT)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_CAR_CODE))))
			country.setCountryCarCode(
					startElement.getAttributeByName(new QName(XmlConstants.COUNTRY_CAR_CODE)).getValue().strip());

		return country;
	}

	// Set Province Values
	private Province setProvinces(StartElement startElement, Province province) {
		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_ID))))
			province.setProvinceId(
					startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_NAME))))
			province.setProvinceName(
					startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_CAPITAL))))
			province.setProvinceCapital(
					startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_CAPITAL)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_POPULATION))))
			province.setProvicePopulation(
					startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_POPULATION)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_AREA))))
			province.setProvinceArea(
					startElement.getAttributeByName(new QName(XmlConstants.PROVINCE_AREA)).getValue().strip());

		return province;
	}

	// Set City Attributes
	private City setCities(StartElement startElement, City city) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.CITY_ID))))
			city.setCityId(startElement.getAttributeByName(new QName(XmlConstants.CITY_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.CITY_LONGITUDE))))
			city.setCityLongitude(
					startElement.getAttributeByName(new QName(XmlConstants.CITY_LONGITUDE)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.CITY_LATITUDE))))
			city.setCityLatitude(
					startElement.getAttributeByName(new QName(XmlConstants.CITY_LATITUDE)).getValue().strip());

		return city;
	}

	// Set Organization Attributes
	private Organization setOrganizationValues(StartElement startElement, Organization organization) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ID))))
			organization.setOrganizationId(
					startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_NAME))))
			organization.setOrganizationName(
					startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ABBREV))))
			organization.setOrganizationAbbrev(
					startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ABBREV)).getValue().strip());

		if (CustomUtility
				.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ESTABLISHED))))
			organization.setOrganizationEstablished(startElement
					.getAttributeByName(new QName(XmlConstants.ORGANIZATION_ESTABLISHED)).getValue().strip());

		if (CustomUtility
				.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ORGANIZATION_HEADQUARTER))))
			organization.setOrganizationHeadQuarter(startElement
					.getAttributeByName(new QName(XmlConstants.ORGANIZATION_HEADQUARTER)).getValue().strip());

		return organization;
	}

	// Set Mountain Values
	private Mountain setMountainValues(StartElement startElement, Mountain mountain) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_ID))))
			mountain.setMountainId(
					startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_NAME))))
			mountain.setMountainName(
					startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_LONGITUDE))))
			mountain.setMountainLongitude(
					startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_LONGITUDE)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_LATITUDE))))
			mountain.setMountainLatitude(
					startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_LATITUDE)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_HEIGHT))))
			mountain.setMountainHeight(
					startElement.getAttributeByName(new QName(XmlConstants.MOUNTAIN_HEIGHT)).getValue().strip());

		return mountain;
	}

	// Set Desert Values
	private Desert setDesertValues(StartElement startElement, Desert desert) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.DESERT_ID))))
			desert.setDesertId(startElement.getAttributeByName(new QName(XmlConstants.DESERT_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.DESERT_NAME))))
			desert.setDesertName(
					startElement.getAttributeByName(new QName(XmlConstants.DESERT_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.DESERT_AREA))))
			desert.setDesertArea(
					startElement.getAttributeByName(new QName(XmlConstants.DESERT_AREA)).getValue().strip());

		return desert;
	}

	// Set Island Values
	private Island setIslandValues(StartElement startElement, Island island) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ISLAND_ID))))
			island.setIslandId(startElement.getAttributeByName(new QName(XmlConstants.ISLAND_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ISLAND_NAME))))
			island.setIslandName(
					startElement.getAttributeByName(new QName(XmlConstants.ISLAND_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.ISLAND_AREA))))
			island.setIslandArea(
					startElement.getAttributeByName(new QName(XmlConstants.ISLAND_AREA)).getValue().strip());

		return island;
	}

	// Set River Values
	private River setRiverValues(StartElement startElement, River river) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.RIVER_ID))))
			river.setRiverId(startElement.getAttributeByName(new QName(XmlConstants.RIVER_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.RIVER_LENGTH))))
			river.setRiverLength(
					startElement.getAttributeByName(new QName(XmlConstants.RIVER_LENGTH)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.RIVER_NAME))))
			river.setRiverName(startElement.getAttributeByName(new QName(XmlConstants.RIVER_NAME)).getValue().strip());

		return river;
	}

	// Set Sea Values
	private Sea setSeaValues(StartElement startElement, Sea sea) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.SEA_ID))))
			sea.setSeaId(startElement.getAttributeByName(new QName(XmlConstants.SEA_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.SEA_NAME))))
			sea.setSeaName(startElement.getAttributeByName(new QName(XmlConstants.SEA_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.SEA_DEPTH))))
			sea.setSeaDepth(startElement.getAttributeByName(new QName(XmlConstants.SEA_DEPTH)).getValue().strip());

		return sea;
	}

	// Set Lake Values
	private Lake setLakeValues(StartElement startElement, Lake lake) {

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.LAKE_ID))))
			lake.setLakeId(startElement.getAttributeByName(new QName(XmlConstants.LAKE_ID)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.LAKE_NAME))))
			lake.setLakeName(startElement.getAttributeByName(new QName(XmlConstants.LAKE_NAME)).getValue().strip());

		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.LAKE_AREA))))
			lake.setLakeArea(startElement.getAttributeByName(new QName(XmlConstants.LAKE_AREA)).getValue().strip());

		return lake;
	}

	// Set Location Country
	private String getLocatedCountry(StartElement startElement) {
		String locatedCountry = null;
		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.LOCATED_COUNTRY))))
			locatedCountry = startElement.getAttributeByName(new QName(XmlConstants.LOCATED_COUNTRY)).getValue()
					.strip();

		return locatedCountry;
	}

	// Set Location Province
	private String getLocatedProvince(StartElement startElement) {
		String locatedProvince = null;
		if (CustomUtility.isObjectNotNull(startElement.getAttributeByName(new QName(XmlConstants.LOCATED_PROVINCE))))
			locatedProvince = startElement.getAttributeByName(new QName(XmlConstants.LOCATED_PROVINCE)).getValue()
					.strip();

		return locatedProvince;

	}

}
