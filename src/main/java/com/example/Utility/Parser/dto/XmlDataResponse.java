package com.example.Utility.Parser.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlDataResponse {

    private String newsPaperName;
    private String height;
    private String width;
    private String dpi;

    private String fileName;

    private String uploadTime;

    private String errorMsg;





}
