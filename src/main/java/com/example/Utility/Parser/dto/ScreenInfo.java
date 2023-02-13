package com.example.Utility.Parser.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "screenInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScreenInfo implements Serializable {

    @XmlAttribute
    private String height;

    @XmlAttribute
    private String width;

    @XmlAttribute
    private String dpi;
}
