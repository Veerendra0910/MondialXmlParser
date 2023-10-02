package com.springboot.mondial.parsexml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mondial.parsexml.dto.BaseResponse;
import com.springboot.mondial.parsexml.service.MondialService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/mondial")
@Slf4j
public class MondialController {

	public static final String TRACE_TAG = "MondialController.";

	@Autowired
	MondialService mondialService;

	@GetMapping("/loadData")
	public BaseResponse saveMondial() {

		log.info(TRACE_TAG + " method saveMondial() Begins ");
		BaseResponse baseResponse = new BaseResponse();
		try {
			baseResponse = mondialService.loadMondialData();
			baseResponse.setMessage(baseResponse.getSuccess() ? "Data Loaded Successfully!" : "Error In Loading Data");
		} catch (Exception e) {
			log.error(TRACE_TAG + " Exception at saveMondial() " + e);
			baseResponse.setSuccess(Boolean.FALSE);
			baseResponse.setErrorMessage(e.getMessage());
		} finally {
			log.info(TRACE_TAG + " method saveMondial() Ends ");
		}
		return baseResponse;
	}
}
