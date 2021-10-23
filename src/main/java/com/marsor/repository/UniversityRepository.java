package com.marsor.repository;

import com.marsor.entity.UniversityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityModel,Long> {

}
