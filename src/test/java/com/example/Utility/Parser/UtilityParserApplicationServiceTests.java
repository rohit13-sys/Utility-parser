package com.example.Utility.Parser;

import com.example.Utility.Parser.dto.*;
import com.example.Utility.Parser.modal.XmlData;
import com.example.Utility.Parser.repository.DataRepository;
import com.example.Utility.Parser.service.XMLParserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class UtilityParserApplicationServiceTests {


	@Autowired
	private XMLParserService service;

	@MockBean
	DataRepository repository;

	@Test
	public void getXMLData(){
		Pageable pageable=PageRequest.of(0,2);
		List<XmlData> responseList=dataForMock();
		Page<XmlData> pageData=new PageImpl<>(responseList);
		Mockito.when(repository.findAll(pageable)).thenReturn(pageData);
		Assert.assertEquals(2,service.allXmlData(pageable).size());

	}

	@Test
	public void parseXmlAndInsertIntoDB() throws IOException, JAXBException, ParserConfigurationException, SAXException {
		XmlData data=new XmlData();
		data=response();
		Path path = Paths.get("data/parse.xml");
		String name = "parse.xml";
		String originalFileName = "parse.xml";
		String contentType = "application/xml";
		byte[] content = null;
		content = Files.readAllBytes(path);
		MultipartFile result = new MockMultipartFile(name,
				originalFileName, contentType, content);
		Mockito.when(repository.save(data)).thenReturn(data);
		Assert.assertEquals("CNN",service.xmlParser(result, result.getOriginalFilename()).getNewsPaperName());
	}

	public List<XmlData> dataForMock(){
		XmlData data=new XmlData();
		List<XmlData> dataList=new ArrayList<>();
		for(int i=0;i<2;i++){
			data.setDpi("160");
			data.setHeight("750");
			data.setNewsPaperName("abb");
			data.setWidth("1280");
			data.setFileName("parse.xml");
			data.setUploadTime("13-02-2023 13:21:16");
			dataList.add(data);
		}
		return dataList;
	}

//	public Page<XmlData> pageData(){
//		XmlData data=response();
//		Page<XmlData> page= new Pageable(PageRequest.of(0,2));
//	}

	public XmlData response(){
		XmlData data=new XmlData();
		data.setDpi("160");
		data.setHeight("750");
		data.setNewsPaperName("abb");
		data.setWidth("1280");
		data.setFileName("parse.xml");
		data.setUploadTime("13-02-2023 13:21:16");
		return data;
	}
	XMLDataRequest request(){
		XMLDataRequest req=new XMLDataRequest();
		req.setDeviceInfo(deviceInfo());
		req.setFileName("parse.xml");
		req.setUploadTime("13-02-2023 13:21:16");
		return req;
	}

	ScreenInfo screenInfo(){
		ScreenInfo screenInfo=new ScreenInfo();
		screenInfo.setDpi("160");
		screenInfo.setHeight("750");
		screenInfo.setWidth("1280");
		return screenInfo;
	}

	DeviceInfo deviceInfo(){
		DeviceInfo deviceInfo=new DeviceInfo();
		deviceInfo.setScreenInfo(screenInfo());
		deviceInfo.setAppInfo(appInfo());
		return deviceInfo;
	}

	AppInfo appInfo(){
		AppInfo info=new AppInfo();
		info.setNewspaperName("abb");
		info.setVersion("1.0");
		return info;
	}


	@Test
	public void getXmlDataByPaging(){
		Pageable pageable=PageRequest.of(0,2);
		List<XmlData> list=dataForMock();
		Page<XmlData> page=new PageImpl<>(list);

		Mockito.when(repository.findAll(pageable)).thenReturn(page);
		Assert.assertEquals(2,service.allXmlData(pageable).size());
	}

	@Test
	public void getXmlDataByName(){
		Pageable pageable=PageRequest.of(0,2);
		List<XmlData> responseList=dataForMock();
		Page<XmlData> pageData=new PageImpl<>(responseList);
		Mockito.when(repository.findAllByNewsPaperNameContains("abb",pageable)).thenReturn(pageData);
		Assert.assertEquals("abb",service.xmlData("abb",pageable).get(0).getNewsPaperName());
	}

	@Test
	public void getXmlDataByNameAndPaging(){
		Pageable pageable=PageRequest.of(0,2);
		List<XmlData> list=dataForMock();
		Page<XmlData> page=new PageImpl<>(list);

		Mockito.when(repository.findAll(pageable)).thenReturn(page);
		Assert.assertEquals(2,service.allXmlData(pageable).size());
	}


}
