package com.example.newprojectoption.dao;

import com.example.newprojectoption.bean.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<ImageModel,Long> {
    Optional<ImageModel> findByName (String name);
}
