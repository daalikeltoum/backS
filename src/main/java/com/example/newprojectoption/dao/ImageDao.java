package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.ImageModl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<ImageModl,Long> {
    Optional<ImageModl> findByName (String name);
    ImageModl findByNameData (String nameData);

}
