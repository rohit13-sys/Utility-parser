package com.example.Utility.Parser.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "deviceInfo")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceInfo implements Serializable {

    private ScreenInfo screenInfo;

    private String osInfo;

    private AppInfo appInfo;
}
