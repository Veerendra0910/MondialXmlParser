DROP SCHEMA [IF EXISTS] mondialdb;  

create table continent_info (
    continent_id varchar(255) not null primary key,
    continent_name varchar(255)
);

create table country_info (
    country_id varchar(255) not null primary key,
    country_name varchar(255),
    country_capital varchar(255),
    country_population varchar(255),
    country_data_code varchar(255),
    country_total_area varchar(255),
    country_population_growth varchar(255),
    country_infant_mortality varchar(255),
    country_gdp_agri varchar(255),
    country_gdp_total varchar(255),
    country_inflation varchar(255),
    country_indeep_date varchar(255),
    country_government varchar(255),
    country_car_code varchar(255)
);

create table province_info  (
    province_id varchar(255) not null primary key,
    province_name varchar(255),
    province_country varchar(255),
    province_capital varchar(255),
    province_population varchar(255),
    province_area varchar(255),
	foreign key (province_country)
    references country_info(country_id)
);

create table city_info  (
    city_id varchar(255) not null primary key,
    city_country varchar(255),
    province_id varchar(255),
	city_names text,
    city_longitude varchar(255),
    city_latitude varchar(255),
    foreign key (city_country)
    references country_info(country_id),
	foreign key (province_id)
    references province_info(province_id)
);

create table organization_info  (
    organization_id varchar(255) not null primary key,
    organization_name varchar(255),
    organization_abbrev varchar(255),
    organization_established varchar(255),
    organization_headquarter varchar(255)
);

create table mountain_info  (
    mountain_id varchar(255) not null primary key, 
    mountain_name varchar(255),
    mountain_longitude varchar(255),
    mountain_latitude varchar(255),
    mountain_height varchar(255),
    mountain_countries text,
    mountain_provinces text
);

create table desert_info  (
    desert_id varchar(255) not null primary key,
    desert_name varchar(255),
    desert_area varchar(255),
    desert_countries text,
    desert_provinces text
);

create table island_info  (
    island_id varchar(255) not null primary key,
    island_name varchar(255),
    island_area varchar(255),
    island_countries text,
    island_provinces text
);

create table river_info  (
    river_id varchar(255) not null primary key, 
    river_name varchar(255),
    river_length varchar(255),
    river_type varchar(255),
    river_water varchar(255),
    river_countries text,
    river_provinces text
);


create table sea_info  (
    sea_id varchar(255) not null primary key,
    sea_name varchar(255),
    sea_depth varchar(255),
    sea_countries text,
    sea_provinces text
);


create table lake_info  (
    lake_id varchar(255) not null primary key,
    lake_name varchar(255),
    lake_area varchar(255),
    lake_countries text,
    lake_provinces text
);

