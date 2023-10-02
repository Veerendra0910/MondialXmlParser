package com.springboot.mondial.parsexml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mondial.parsexml.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

}
