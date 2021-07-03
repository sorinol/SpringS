package com.bogdanbrl.repository;

import com.bogdanbrl.entity.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

    CountryModel findByName(String name);
}
