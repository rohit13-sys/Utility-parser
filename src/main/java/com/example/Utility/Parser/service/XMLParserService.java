package com.example.Utility.Parser.service;

import com.example.Utility.Parser.dto.XmlDataResponse;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface XMLParserService {

    XmlDataResponse xmlParser(MultipartFile file, String fileName) throws ParserConfigurationException, IOException, SAXException, JAXBException;

    List<XmlDataResponse> xmlData();

    List<XmlDataResponse> xmlDataByPaging(int pageNo);

    boolean isValid(String xsdPath, File file) throws IOException, SAXException;


    File convert(MultipartFile file) throws IOException;

    XmlDataResponse xmlDataByName(String newsPaperName);
}
