package com.example.Utility.Parser.controller;

import com.example.Utility.Parser.dto.XMLDataRequest;
import com.example.Utility.Parser.dto.XmlDataResponse;
import com.example.Utility.Parser.service.XMLParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xml-parser")
public class DataController {
    @Autowired
    private XMLParserService xmlParserService;

    @PostMapping("/parse")
    public ResponseEntity<XmlDataResponse> xmlparser(@RequestPart("file") MultipartFile file) throws IOException {

        XmlDataResponse response = new XmlDataResponse();
        File xmlFile = xmlParserService.convert(file);
        try {

            //converting multipart file into file


            //checking whether xml file is valid or not as given in xsd schema
            boolean isValid = xmlParserService.isValid(System.getProperty("user.dir")+"/data/parse.xsd", xmlFile);

            if (isValid) {
                String fileName = file.getOriginalFilename();

                //parse the xml
                response = xmlParserService.xmlParser(file, fileName);

                xmlFile.delete();
                return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
            } else {

                xmlFile.delete();
                throw new RuntimeException("Invlid XMl file Not matched with given xsd schema");

            }

        } catch (JAXBException | RuntimeException | SAXException e) {
            response.setErrorMsg("Invalid Xml file");

            xmlFile.delete();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getXmlData")
    public ResponseEntity<List<XmlDataResponse>> xmlDataByName(@RequestParam(name = "newsPaperName", required = false) String newsPaperName , Pageable pageable) {
        List<XmlDataResponse> response = new ArrayList<>();
        String currentDirectory = System.getProperty("user.dir");
        System.out.println(currentDirectory);
        if(newsPaperName!=null){
            response=xmlParserService.xmlData(newsPaperName,pageable);
            System.out.println(response.size());
        }else {
            response=xmlParserService.allXmlData(pageable);
            System.out.println(response.size());
        }

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
