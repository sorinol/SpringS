package com.marsor.controller;

import com.marsor.dto.FacultyDto;
import com.marsor.entity.UniversityModel;
import com.marsor.entity.FacultyModel;
import com.marsor.service.UniversityService;
import com.marsor.service.FacultyService;
import com.marsor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FacultyController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

//    @GetMapping("/offers/reservations")
//    public ResponseEntity getReservations(@RequestParam("id") Long id) {
//        List<UserModel> customers = offerService.getCustomer(id);
//        return new ResponseEntity(customers, HttpStatus.OK);
//    }

    @PostMapping("/faculties/addFaculty")
    public ResponseEntity addFaculty(@RequestBody FacultyDto facultyDto) {
        UniversityModel universityModel = universityService.getById(facultyDto.getUniversityId());
        if (universityModel == null) {
            return new ResponseEntity("Universitate invalida!", HttpStatus.BAD_REQUEST);
        }
        FacultyModel facultyModel = new FacultyModel();
        facultyModel.setName(facultyDto.getName());
        facultyModel.setDescription(facultyDto.getDescription());
        facultyModel.setContactNumber(facultyDto.getContactNumber());
        facultyModel.setUniversity(universityModel);
        facultyModel.setImageUrl(facultyDto.getImageUrl());

        facultyService.addFaculty(facultyModel);
        return new ResponseEntity(facultyModel, HttpStatus.OK);
    }

    @PutMapping("/faculties/editFaculty/{id}")
    public ResponseEntity editFaculty(@PathVariable("id") Long id, @RequestBody FacultyDto facultyDto) {

        FacultyModel facultyModel = facultyService.getFacultyById(id);
        if (facultyModel == null) {
            return new ResponseEntity(" Facultatea nu exista", HttpStatus.BAD_REQUEST);
        }

        UniversityModel universityModel = universityService.getById(facultyDto.getUniversityId());
        if (universityModel == null) {
            return new ResponseEntity("Universitate invalida!", HttpStatus.BAD_REQUEST);
        }

        facultyModel.setName(facultyDto.getName());
        facultyModel.setDescription(facultyDto.getDescription());
        facultyModel.setContactNumber(facultyDto.getContactNumber());
        facultyModel.setUniversity(universityModel);
        facultyModel.setImageUrl(facultyDto.getImageUrl());

        facultyService.editFaculty(facultyModel);
        return new ResponseEntity(facultyModel, HttpStatus.OK);
    }

    @DeleteMapping("/faculties/deleteFaculty/{id}")
    public ResponseEntity deleteFacultyById(@PathVariable("id") Long id){
        facultyService.deleteFacultyById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/faculties/getFaculties")
    public ResponseEntity getFaculties() {
        List<FacultyModel> faculties = facultyService.getFaculties();
        return new ResponseEntity(faculties, HttpStatus.OK);
    }

    @GetMapping("/faculties/getFacultyById/{id}")
    public ResponseEntity getFacultyById(@PathVariable("id") Long id) {
        FacultyModel faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return new ResponseEntity("facultatea nu exista", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(faculty, HttpStatus.OK);
    }

//    @PostMapping("/faculties/buyOffer")
//    public ResponseEntity buyOffer(@RequestBody BuyOfferDto buyOfferDto){
//        FacultyModel offerModel = facultyService.getFacultyById(buyOfferDto.getOfferId());
//        List<UserModel> customers = offerModel.getCustomers();
//
//        UserModel userModel = userService.getUserById(buyOfferDto.getUserId());
//        if (userModel == null) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        customers.add(userModel);
//        offerModel.setCustomers(customers);
//        facultyService.editFaculty(offerModel);
//
//        return new ResponseEntity(offerModel, HttpStatus.OK);
//    }
}
