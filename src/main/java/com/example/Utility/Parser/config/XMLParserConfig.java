package com.example.Utility.Parser.config;

import com.example.Utility.Parser.dto.XMLDataRequest;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class XMLParserConfig {


//    public static void main(String[] args) throws SAXException, IOException,
//            ParserConfigurationException, TransformerException {
////
////        XMLDataRequest data = new XMLDataRequest();
////        Calendar cal = Calendar.getInstance();
////        Date date=cal.getTime();
////        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
////        String formattedDate=dateFormat.format(date);
////        data.setUploadTime(formattedDate);
////        System.out.println(formattedDate);
////
////        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
////                .newInstance();
////        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
////        Document document = docBuilder.parse(new File("data/parse.xml"));
////
////        NodeList nodeList = document.getElementsByTagName("*");
////        for (int i = 0; i < nodeList.getLength(); i++) {
////            Node node = nodeList.item(i);
////            if (node.getNodeType() == Node.ELEMENT_NODE) {
////
////                if (node.getNodeName().equalsIgnoreCase("screeninfo")) {
////                    System.out.println(node.getNodeName());
////                    data.setWidth(node.getAttributes().getNamedItem("width").getTextContent());
////                    data.setHeight(node.getAttributes().getNamedItem("height").getTextContent());
////                    data.setDpi(node.getAttributes().getNamedItem("dpi").getTextContent());
////
////                }
////                if (node.getNodeName().equalsIgnoreCase("newspaperName")) {
////                    System.out.println(node.getNodeName());
////                    data.setNewsPaperName(node.getTextContent());
////                }
////            }
////        }
////    }
}
