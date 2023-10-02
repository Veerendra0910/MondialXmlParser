package com.springboot.mondial.parsexml.service;

import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.mondial.parsexml.dao.MondialDao;
import com.springboot.mondial.parsexml.dto.BaseResponse;
import com.springboot.mondial.parsexml.entity.MondialEntity;
import com.springboot.mondial.parsexml.parser.MondialXmlParser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MondialService {

	public static final String TRACE_TAG = "MondialService.";

	@Autowired
	MondialDao mondialDao;

	@Autowired
	MondialXmlParser xmlParser;

	@Value("${xml.file.path}")
	private String xmlFilePath;

	// Call ModialDao to Parse and save Data to Database
	public BaseResponse loadMondialData() {
		log.info(TRACE_TAG + " method loadMondialData() Begins ");
		BaseResponse baseResponse = new BaseResponse();
		MondialEntity mondialEntity = new MondialEntity();
		try {
			mondialEntity = xmlParser.parserXml(xmlFilePath);
			baseResponse = mondialDao.addMondial(mondialEntity);
		} catch (XMLStreamException e) {
			log.error(TRACE_TAG + " Exception at saveMondial() " + e);
			baseResponse.setSuccess(Boolean.FALSE);
			baseResponse.setErrorMessage(e.getMessage());
		} finally {
			log.info(TRACE_TAG + " method saveMondial() Ends ");
		}
		return baseResponse;
	}
}
