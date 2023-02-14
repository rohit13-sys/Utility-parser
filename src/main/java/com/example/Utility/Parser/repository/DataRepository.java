package com.example.Utility.Parser.repository;


import com.example.Utility.Parser.modal.XmlData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DataRepository extends JpaRepository<XmlData,Long> {

    Page<XmlData> findAllByNewsPaperNameContains(String newsPaperName,Pageable pageable);

}
