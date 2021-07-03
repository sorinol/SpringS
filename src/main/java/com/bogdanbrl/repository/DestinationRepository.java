package com.bogdanbrl.repository;

import com.bogdanbrl.entity.TravelDestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<TravelDestinationModel,Long> {

}
