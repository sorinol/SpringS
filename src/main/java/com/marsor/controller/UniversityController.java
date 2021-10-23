package com.marsor.controller;

import com.marsor.dto.UniversityDto;
import com.marsor.entity.CityModel;
import com.marsor.entity.UniversityModel;
import com.marsor.entity.FacultyModel;
import com.marsor.service.CityService;
import com.marsor.service.UniversityService;
import com.marsor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @GetMapping("/university/getUniversityById/{id}")
    public ResponseEntity getUniversityById(@PathVariable Long id) {
        UniversityModel university = universityService.getById(id);
        return new ResponseEntity(university, HttpStatus.OK);
    }

    @GetMapping("/university/getUniversities")
    public ResponseEntity getDestinations() {

        List<UniversityModel> universities = universityService.getAll();

        return new ResponseEntity(universities, HttpStatus.OK);
    }

    @PostMapping("/university/addUniversity")
    public ResponseEntity addUniversity(@RequestBody UniversityDto universityDto) {
        CityModel cityModel = cityService.getById(universityDto.getCityId());
        if (cityModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        UniversityModel university = new UniversityModel();
        university.setName(universityDto.getName());
        university.setDescription(universityDto.getDescription());
        university.setCity(cityModel);

        universityService.addUniversity(university);
        return new ResponseEntity(university, HttpStatus.OK);
    }

    @PutMapping("/university/editUniversity/{id}")
    public ResponseEntity editUniversity(@PathVariable("id") Long id, @RequestBody UniversityDto universityDto) {
        CityModel cityModel = cityService.getById(universityDto.getCityId());
        if (cityModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        UniversityModel university = new UniversityModel();
        university.setId(id);
        university.setId(universityDto.getId());
        university.setName(universityDto.getName());
        university.setDescription(universityDto.getDescription());
        university.setCity(cityModel);

        universityService.editUniversity(university);
        return new ResponseEntity(university, HttpStatus.OK);
    }

    @DeleteMapping("/university/deleteUniversity/{id}")
    public ResponseEntity deleteUniversity(@PathVariable("id") Long id) {
        universityService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/university/faculties")
    public ResponseEntity viewFaculties(@RequestParam("universityId") Long universityId) {
        UniversityModel university = universityService.getById(universityId);
        List<FacultyModel> faculties = university.getFacultyModelList();
        return new ResponseEntity(faculties, HttpStatus.OK);
    }
}
