package com.example.Utility.Parser.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xmldata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "newsPaperName")
    private String newsPaperName;
    @Column(name = "height")
    private String height;

    @Column(name = "width")
    private String width;
    @Column(name = "dpi")
    private String dpi;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "uploadTime")
    private String uploadTime;
}
