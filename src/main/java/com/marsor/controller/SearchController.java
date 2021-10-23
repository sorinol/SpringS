package com.marsor.controller;

import com.marsor.entity.FacultyModel;
import com.marsor.service.FacultyService;
import com.marsor.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;


    @GetMapping("/searchFaculty")
    public ResponseEntity searchFaculty(@RequestParam("universityId") Long universityId) {
        List<FacultyModel> faculties = facultyService.findFaculties(universityId);
        return new ResponseEntity(faculties, HttpStatus.OK);
    }
}
