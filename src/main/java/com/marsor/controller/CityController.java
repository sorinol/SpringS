package com.marsor.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marsor.dto.CityDto;
import com.marsor.entity.CityModel;
import com.marsor.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;


    @PostMapping("/city/addCity")
    public ResponseEntity addCity(@RequestBody CityDto cityDto){

        CityModel cityModel = cityService.getById(cityDto.getId());
        if (cityModel != null) {
            return new ResponseEntity(cityDto, HttpStatus.BAD_REQUEST);
        }
        CityModel newCity = new CityModel();
        newCity.setName(cityDto.getName());
        cityService.addCity(newCity);

        return new ResponseEntity(newCity, HttpStatus.OK);
    }

    @PutMapping("/city/editCity/{id}")
    public ResponseEntity editCity(@PathVariable("id") Long id, @RequestBody CityDto cityDto){
        CityModel cityModel = cityService.getById(cityDto.getId());
        if (cityModel != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        CityModel city= new CityModel();
        city.setId(id);
        city.setName(cityDto.getName());
        cityService.editCity(city);

        return new ResponseEntity(city, HttpStatus.OK);
    }

    @DeleteMapping("/city/deleteCity/{id}")
    public ResponseEntity deleteCity(@PathVariable("id") Long id){
        cityService.deleteCity(id);
        return new ResponseEntity(HttpStatus.OK);
    }
@JsonIgnore
    @GetMapping("/city/getCities")

    public ResponseEntity getCities(){
        List<CityModel> cities = cityService.getCities();

        return new ResponseEntity(cities, HttpStatus.OK);
    }

    @GetMapping("/city/getCityById/{id}")
    public ResponseEntity getCityById(@PathVariable("id") Long id){
        CityModel city = cityService.getById(id);
        return new ResponseEntity(city, HttpStatus.OK);
    }
}
