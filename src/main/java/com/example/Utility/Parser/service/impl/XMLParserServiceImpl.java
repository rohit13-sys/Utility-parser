package com.example.Utility.Parser.service.impl;

import com.example.Utility.Parser.dto.XMLDataRequest;
import com.example.Utility.Parser.dto.XmlDataResponse;
import com.example.Utility.Parser.modal.XmlData;
import com.example.Utility.Parser.repository.DataRepository;
import com.example.Utility.Parser.service.XMLParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.ResourceUtils.getFile;

@Service
public class XMLParserServiceImpl implements XMLParserService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public XmlDataResponse xmlParser(MultipartFile file, String fileName) throws JAXBException {

        try {

            XMLDataRequest data = new XMLDataRequest();

            JAXBContext jaxbContext;

            //initializing new instance of jaxbcontext
            // with structure similar to XMLDataRequest class
            jaxbContext = JAXBContext.newInstance(XMLDataRequest.class);

            //initializing object of unmarshaller to deserialize xml file
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //storing contents of file in string object
            String content = new String(file.getBytes());

            //unmarshal or convert xml into given java class or given java class tree
            data = (XMLDataRequest) jaxbUnmarshaller.unmarshal(new StringReader(content));

            //generating current date and time for getting upload time of file
            String formattedDate = getUploadTime();
            data.setUploadTime(formattedDate);
            data.setFileName(fileName);
//            System.out.println(data);

            //save to database
            saveDataToDB(data);

            //sent response to client
            XmlDataResponse response = convertreqToRes(data);

            return response;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<XmlDataResponse> xmlData(String newsPaperName,Pageable pageable) {
        List<XmlDataResponse> response = new ArrayList<>();
        List<XmlData> dataList;
        Page<XmlData> list = dataRepository.findAllByNewsPaperNameContains(newsPaperName,PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<XmlData> data=list.getContent();
        response=data.stream().map(this::mapToResponse).collect(Collectors.toList());
        return response;
    }


    @Transactional
    public void saveDataToDB(XMLDataRequest req) {
        XmlData data = new XmlData();
        data.setDpi(req.getDeviceInfo().getScreenInfo().getDpi());
        data.setHeight(req.getDeviceInfo().getScreenInfo().getHeight());
        data.setWidth(req.getDeviceInfo().getScreenInfo().getWidth());
        data.setUploadTime(req.getUploadTime());
        data.setFileName(req.getFileName());
        data.setNewsPaperName(req.getDeviceInfo().getAppInfo().getNewspaperName());
        dataRepository.save(data);
    }

    public String getUploadTime() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(date);
    }

    public XmlDataResponse convertreqToRes(XMLDataRequest req) {
        XmlDataResponse response = new XmlDataResponse();
        response.setDpi(req.getDeviceInfo().getScreenInfo().getDpi());
        response.setHeight(req.getDeviceInfo().getScreenInfo().getHeight());
        response.setWidth(req.getDeviceInfo().getScreenInfo().getWidth());
        response.setFileName(req.getFileName());
        response.setUploadTime(req.getUploadTime());
        response.setNewsPaperName(req.getDeviceInfo().getAppInfo().getNewspaperName());
        response.setErrorMsg("");
        return response;
    }

    public XmlDataResponse mapToResponse(XmlData data) {
        XmlDataResponse response = new XmlDataResponse();
        response.setUploadTime(data.getUploadTime());
        response.setDpi(data.getDpi());
        response.setHeight(data.getHeight());
        response.setWidth(data.getWidth());
        response.setNewsPaperName(data.getNewsPaperName());
        response.setFileName(data.getFileName());
        return response;
    }

    private Validator initValidator(String xsdPath) throws SAXException, FileNotFoundException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getFile(xsdPath));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }

    @Override
    public boolean isValid(String xsdPath, File file) throws IOException, SAXException {
        Validator validator = initValidator(xsdPath);
        try {
            validator.validate(new StreamSource(file));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }


    @Override
    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public List<XmlDataResponse> allXmlData(Pageable pageable) {
        Page<XmlData> page = dataRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<XmlData> pageData=page.getContent();
        List<XmlDataResponse> response=pageData.stream().map(this::mapToResponse).collect(Collectors.toList());
        return response;
    }
}
