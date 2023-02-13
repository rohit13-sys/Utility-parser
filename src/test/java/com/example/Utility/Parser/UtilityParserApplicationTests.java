package com.example.Utility.Parser;

import com.example.Utility.Parser.dto.XMLDataRequest;
import com.example.Utility.Parser.dto.XmlDataResponse;
import com.example.Utility.Parser.modal.XmlData;
import com.example.Utility.Parser.service.XMLParserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class UtilityParserApplicationTests {


	@MockBean
	private XMLParserService service;

	@Test
	public void getXMLData(){
		List<XmlDataResponse> t=new ArrayList<>();
		Mockito.when(service.xmlData()).thenReturn(t);
		Assert.assertEquals(t.size(),0);

	}

	@Test
	public void parseXmlAndInsertIntoDB() throws IOException, JAXBException, ParserConfigurationException, SAXException {
		XmlData data=new XmlData();
		data.setDpi("160");
		data.setHeight("750");
		data.setNewsPaperName("abb");
		data.setWidth("1280");
		data.setFileName("parse.xml");
		data.setUploadTime("13-02-2023 13:21:16");
		XmlDataResponse response=new XmlDataResponse();
		File file = new File("data/parse.xml");
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file",
				file.getName().getBytes());
		Mockito.when(service.xmlParser(multipartFile,"parse.xml")).thenReturn(response);

	}


}
