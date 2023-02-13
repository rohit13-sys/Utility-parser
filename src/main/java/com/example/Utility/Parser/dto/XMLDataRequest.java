package com.example.Utility.Parser.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "epaperRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class XMLDataRequest {

    private static final long serialVersionUID = 1L;

    private DeviceInfo deviceInfo;

    private String getPages;

    private String fileName;

    private String uploadTime;

}
