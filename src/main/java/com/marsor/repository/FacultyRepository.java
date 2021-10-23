package com.marsor.repository;

import com.marsor.entity.FacultyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyModel, Long> {

    @Query("from FacultyModel as faculties join faculties.university as universities " +
            "where universities.id = :universityId")
    List<FacultyModel> findFaculties(@Param("universityId") long universityId);

}
