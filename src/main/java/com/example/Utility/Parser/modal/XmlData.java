package com.example.Utility.Parser.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "xmlData")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class XmlData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String newsPaperName;
    private String height;
    private String width;
    private String dpi;

    private String fileName;

    private String uploadTime;
}
