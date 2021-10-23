package com.marsor.repository;

import com.marsor.entity.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityModel, Long> {

   CityModel findByName(String name);


}
