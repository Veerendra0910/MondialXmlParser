package com.springboot.mondial.parsexml.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.mondial.parsexml.entity.Continent;

public interface ContinentRepository extends JpaRepository<Continent, String> {

}
