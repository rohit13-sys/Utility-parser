package com.example.Utility.Parser.repository;

import com.example.Utility.Parser.dto.XmlDataResponse;
import com.example.Utility.Parser.modal.XmlData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataRepository extends JpaRepository<XmlData,Long> {


//    List<XmlData> findByNewsPaperNameContains(String newsPaperName);

    Page<XmlData> findAllByNewsPaperNameContains(String newsPaperName,Pageable pageable);

}
