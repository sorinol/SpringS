package com.bogdanbrl.repository;

import com.bogdanbrl.entity.ContinentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentModel, Long> {

    ContinentModel findByName(String name);
}
