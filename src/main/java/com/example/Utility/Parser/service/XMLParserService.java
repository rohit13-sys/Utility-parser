package com.example.Utility.Parser.service;

import com.example.Utility.Parser.dto.XmlDataResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface XMLParserService {

    XmlDataResponse xmlParser(MultipartFile file, String fileName) throws JAXBException;

    List<XmlDataResponse> xmlData(String newsPaperName,Pageable pageable);

//    List<XmlDataResponse> xmlDataByPaging(int pageNo,int pageSize);

    boolean isValid(String xsdPath, File file) throws IOException, SAXException;


    File convert(MultipartFile file) throws IOException;

    List<XmlDataResponse> allXmlData(Pageable pageable);

//    List<XmlDataResponse> xmlDataByName(String newsPaperName);
//
//    List<XmlDataResponse> xmlDataByNameAndPaging(String newsPaperName, Pageable pageable);
}
