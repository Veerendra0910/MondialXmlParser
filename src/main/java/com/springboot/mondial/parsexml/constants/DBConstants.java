package com.springboot.mondial.parsexml.constants;

public class DBConstants {

	// DB Continent Table
	public static final String DB_CONTINENT_TABLE = "continent_info";
	public static final String DB_CONTINENT_COLUMN_ID = "continent_id";
	public static final String DB_CONTINENT_COLUMN_NAME = "continent_name";

	// DB Country Table
	public static final String DB_COUNTRY_TABLE = "country_info";
	public static final String DB_COUNTRY_COLUMN_ID = "country_id";
	public static final String DB_COUNTRY_COLUMN_NAME = "country_name";
	public static final String DB_COUNTRY_COLUMN_CAPITAL = "country_capital";
	public static final String DB_COUNTRY_COLUMN_POPULATION = "country_population";
	public static final String DB_COUNTRY_COLUMN_DATA_CODE = "country_data_code";
	public static final String DB_COUNTRY_COLUMN_TOTAL_AREA = "country_total_area";
	public static final String DB_COUNTRY_COLUMN_POPULATION_GROWTH = "country_population_growth";
	public static final String DB_COUNTRY_COLUMN_INFANT_MORTALITY = "country_infant_mortality";
	public static final String DB_COUNTRY_COLUMN_GDP_AGRI = "country_gdp_agri";
	public static final String DB_COUNTRY_COLUMN_GDP_TOTAL = "country_gdp_total";
	public static final String DB_COUNTRY_COLUMN_INFLATION = "country_inflation";
	public static final String DB_COUNTRY_COLUMN_INDEEP_DATE = "country_indeep_date";
	public static final String DB_COUNTRY_COLUMN_GOVERNMENT = "country_government";
	public static final String DB_COUNTRY_COLUMN_CAR_CODE = "country_car_code";
	public static final String DB_COUNTRY_COLUMN_MAPPEDBY = "country";
	
	public static final String DB_COUNTRY_COLLETIONTABLE_COUTRY_PARAMETERS = "country_parameters";
	public static final String DB_COUNTRY_ETHNICS_GROUPS_NAME = "ethnics_groups_name";
	public static final String DB_COUNTRY_ETHNICS_GROUPS_PERCENTAGE = "ethnics_groups_percentage";
	public static final String DB_COUNTRY_RELIGIONS_NAME = "religions_name";
	public static final String DB_COUNTRY_RELIGIONS_PERCENTAGE = "religions_percentage";
	public static final String DB_COUNTRY_ENCOMPASSED_CONTINENT = "encompassed_continent";
	public static final String DB_COUNTRY_ENCOMPASSED_PERCENTAGE = "encompassed_percentage";
	public static final String DB_COUNTRY_BORDER_COUNTRY = "border_country";
	public static final String DB_COUNTRY_BORDER_LENGTH = "border_length";

	// DB Province Table
	public static final String DB_PROVINCE_TABLE = "province_info";
	public static final String DB_PROVINCE_COLUMN_ID = "province_id";
	public static final String DB_PROVINCE_COLUMN_NAME = "province_name";
	public static final String DB_PROVINCE_COLUMN_COUNTRY = "province_country";
	public static final String DB_PROVINCE_COLUMN_CAPITAL = "province_capital";
	public static final String DB_PROVINCE_COLUMN_POPULATION = "province_population";
	public static final String DB_PROVINCE_COLUMN_AREA = "province_area";
	public static final String DB_PROVINCE_COUNTRY_JOIN_COLUMN = "province_country";

	// DB City Table
	public static final String DB_CITY_TABLE = "city_info";
	public static final String DB_CITY_COLUMN_ID = "city_id";
	public static final String DB_CITY_COLUMN_PROVINCE_ID = "province_id";
	public static final String DB_CITY_COLUMN_LONGITUDE = "city_longitude";
	public static final String DB_CITY_COLUMN_LATITUDE = "city_latitude";
	public static final String DB_CITY_COLUMN_NAMES = "city_names";	
	public static final String DB_CITY_COLUMN_POPULATION_YEAR = "population_year";
	public static final String DB_CITY_COLUMN_POPULATION = "population";
	public static final String DB_CITY_COLLECTIONTABLE_POPULATION = "city_population";
	public static final String DB_CITY_COLUMN_LOCATION_TYPE = "location_type";
	public static final String DB_CITY_COLUMN_LOCATION_WATER = "location_water";
	public static final String DB_CITY_COLLECTIONTABLE_LOCATION = "city_location";
	public static final String DB_CITY_COUNTRY_JOIN_COLUMN = "city_country";
	public static final String DB_CITY_PROVINCE_JOIN_COLUMN = "province_id";

	// DB Organization Table
	public static final String DB_ORGANIZATION_TABLE = "organization_info";
	public static final String DB_ORGANIZATION_COLUMN_ID = "organization_id";
	public static final String DB_ORGANIZATION_COLUMN_NAME = "organization_name";
	public static final String DB_ORGANIZATION_COLUMN_ABBREV = "organization_abbrev";
	public static final String DB_ORGANIZATION_COLUMN_ESTABLISHED = "organization_established";
	public static final String DB_ORGANIZATION_COLUMN_HEADQ = "organization_headquarter";
	public static final String DB_ORGANIZATION_COLLECTIONTABLE_MEMBERS = "organization_member";
	public static final String DB_ORGANIZATION_MEMBER_COUNTRY = "member_country";
	public static final String DB_ORGANIZATION_MEMBER_TYPE = "member_type";

	// DB Mountain Table
	public static final String DB_MOUNTAIN_TABLE = "mountain_info";
	public static final String DB_MOUNTAIN_COLUMN_ID = "mountain_id";
	public static final String DB_MOUNTAIN_COLUMN_NAME = "mountain_name";
	public static final String DB_MOUNTAIN_COLUMN_LONGITUDE = "mountain_longitude";
	public static final String DB_MOUNTAIN_COLUMN_LATITUDE = "mountain_latitude";
	public static final String DB_MOUNTAIN_COLUMN_HEIGHT = "mountain_height";
	public static final String DB_MOUNTAIN_COLUMN_COUNTRIES = "mountain_countries";
	public static final String DB_MOUNTAIN_COLUMN_PROVINCES = "mountain_provinces";

	// DB Desert Table
	public static final String DB_DESERT_TABLE = "desert_info";
	public static final String DB_DESERT_COLUMN_ID = "desert_id";
	public static final String DB_DESERT_COLUMN_NAME = "desert_name";
	public static final String DB_DESERT_COLUMN_AREA = "desert_area";
	public static final String DB_DESERT_COLUMN_COUNTRIES = "desert_countries";
	public static final String DB_DESERT_COLUMN_PROVINCES = "desert_provinces";

	// DB Desert Table
	public static final String DB_ISLAND_TABLE = "island_info";
	public static final String DB_ISLAND_COLUMN_ID = "island_id";
	public static final String DB_ISLAND_COLUMN_NAME = "island_name";
	public static final String DB_ISLAND_COLUMN_AREA = "island_area";
	public static final String DB_ISLAND_COLUMN_COUNTRIES = "island_countries";
	public static final String DB_ISLAND_COLUMN_PROVINCES = "island_provinces";

	// DB River Table
	public static final String DB_RIVER_TABLE = "river_info";
	public static final String DB_RIVER_COLUMN_ID = "river_id";
	public static final String DB_RIVER_COLUMN_NAME = "river_name";
	public static final String DB_RIVER_COLUMN_LENGTH = "river_length";
	public static final String DB_RIVER_COLUMN_TYPE = "river_type";
	public static final String DB_RIVER_COLUMN_WATER = "river_water";
	public static final String DB_RIVER_COLUMN_COUNTRIES = "river_countries";
	public static final String DB_RIVER_COLUMN_PROVINCES = "river_provinces";

	// DB Sea Table
	public static final String DB_SEA_TABLE = "sea_info";
	public static final String DB_SEA_COLUMN_ID = "sea_id";
	public static final String DB_SEA_COLUMN_NAME = "sea_name";
	public static final String DB_SEA_COLUMN_DEPTH = "sea_depth";
	public static final String DB_SEA_COLUMN_COUNTRIES = "sea_countries";
	public static final String DB_SEA_COLUMN_PROVINCES = "sea_provinces";

	// DB Lake Table
	public static final String DB_LAKE_TABLE = "lake_info";
	public static final String DB_LAKE_COLUMN_ID = "lake_id";
	public static final String DB_LAKE_COLUMN_NAME = "lake_name";
	public static final String DB_LAKE_COLUMN_AREA = "lake_area";
	public static final String DB_LAKE_COLUMN_COUNTRIES = "lake_countries";
	public static final String DB_LAKE_COLUMN_PROVINCES = "lake_provinces";
	
}
