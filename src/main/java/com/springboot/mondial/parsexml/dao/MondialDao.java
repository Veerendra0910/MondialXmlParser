package com.springboot.mondial.parsexml.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.mondial.parsexml.dto.BaseResponse;
import com.springboot.mondial.parsexml.entity.MondialEntity;
import com.springboot.mondial.parsexml.repository.ContinentRepository;
import com.springboot.mondial.parsexml.repository.CountryRepository;
import com.springboot.mondial.parsexml.repository.DesertRepository;
import com.springboot.mondial.parsexml.repository.IslandRepository;
import com.springboot.mondial.parsexml.repository.LakeRepository;
import com.springboot.mondial.parsexml.repository.MountainRepository;
import com.springboot.mondial.parsexml.repository.OrganizationRepository;
import com.springboot.mondial.parsexml.repository.RiverRepository;
import com.springboot.mondial.parsexml.repository.SeaRepository;
import com.springboot.mondial.parsexml.utility.CustomUtility;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MondialDao {

	public static final String TRACE_TAG = "MondialDao.";

	@Autowired
	ContinentRepository continentRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	MountainRepository mountainRepository;

	@Autowired
	DesertRepository desertRepository;

	@Autowired
	IslandRepository islandRepository;

	@Autowired
	RiverRepository riverRepository;

	@Autowired
	SeaRepository seaRepository;

	@Autowired
	LakeRepository lakeRepository;

	public BaseResponse addMondial(MondialEntity mondial) {

		log.info(TRACE_TAG + " method addMondial() Starts ");

		BaseResponse baseResponse = new BaseResponse();
		Boolean success = Boolean.FALSE;
		try {
			if (CustomUtility.isObjectNotNull(mondial)) {

				if (CustomUtility.isListNotNull(mondial.getContinents())) {
					continentRepository.saveAll(mondial.getContinents());
					log.info("Continents Saved Successfully!!");
					success = Boolean.TRUE;
				}
				
				if (CustomUtility.isListNotNull(mondial.getCountries())) {
					System.out.println("Country Size " + mondial.getCountries().size());
					countryRepository.saveAll(mondial.getCountries());
					log.info("Countries Saved Successfully!!");
					success = Boolean.TRUE;
				}


				if (CustomUtility.isListNotNull(mondial.getOrganizations())) {
					organizationRepository.saveAll(mondial.getOrganizations());
					log.info("Organizations Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getMountains())) {
					mountainRepository.saveAll(mondial.getMountains());
					log.info("Mountains Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getDeserts())) {
					desertRepository.saveAll(mondial.getDeserts());
					log.info("Deserts Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getIslands())) {
					islandRepository.saveAll(mondial.getIslands());
					log.info("Islands Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getRivers())) {
					riverRepository.saveAll(mondial.getRivers());
					log.info("Rivers Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getSeas())) {
					seaRepository.saveAll(mondial.getSeas());
					log.info("Seas Saved Successfully!!");
					success = Boolean.TRUE;
				}

				if (CustomUtility.isListNotNull(mondial.getLakes())) {
					lakeRepository.saveAll(mondial.getLakes());
					log.info("Lakes Saved Successfully!!");
					success = Boolean.TRUE;
				}

			}
			baseResponse.setSuccess(success);
		} catch (Exception e) {
			log.error(TRACE_TAG + " Exception at addMondial() " + e.getMessage());
			baseResponse.setSuccess(Boolean.FALSE);
			baseResponse.setErrorMessage(e.getMessage());
		} finally {
			log.info(TRACE_TAG + " method addMondial() Ends ");
		}

		return baseResponse;
	}
}
