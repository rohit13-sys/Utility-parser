package com.example.Utility.Parser.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xml_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlData {

    @Id
    private long id;

    @Column(name = "news_paper_name")
    private String newsPaperName;
    @Column(name = "height")
    private String height;

    @Column(name = "width")
    private String width;
    @Column(name = "dpi")
    private String dpi;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "upload_time")
    private String uploadTime;
}
