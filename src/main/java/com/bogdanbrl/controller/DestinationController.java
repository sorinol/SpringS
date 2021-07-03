package com.bogdanbrl.controller;

import com.bogdanbrl.dto.DestinationDto;
import com.bogdanbrl.entity.CountryModel;
import com.bogdanbrl.entity.TravelDestinationModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.service.ContinentService;
import com.bogdanbrl.service.CountryService;
import com.bogdanbrl.service.DestinationService;
import com.bogdanbrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;


    @GetMapping("/getDestinations")
    public ResponseEntity getDestinations() {

        List<TravelDestinationModel> destinations = destinationService.getAll();

        return new ResponseEntity(destinations, HttpStatus.OK);
    }

    @PostMapping("/addDestination")
    public ResponseEntity addDestination(@RequestBody DestinationDto destinationDto) {
        CountryModel countryModel = countryService.getById(destinationDto.getCountryId());
        if (countryModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        TravelDestinationModel destination = new TravelDestinationModel();
        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());
        destination.setCountry(countryModel);

        destinationService.addDestination(destination);
        return new ResponseEntity(destination, HttpStatus.OK);
    }

    @PutMapping("/editDestination/{id}")
    public ResponseEntity editDestination(@PathVariable("id") Long id, @RequestBody DestinationDto destinationDto) {
        CountryModel countryModel = countryService.getById(destinationDto.getCountryId());
        if (countryModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        TravelDestinationModel destination = new TravelDestinationModel();
        destination.setId(id);
//        destination.setId(destinationDto.getId());
        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());
        destination.setCountry(countryModel);

        destinationService.editDestination(destination);
        return new ResponseEntity(destination, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDestination/{id}")
    public ResponseEntity deleteDestination(@PathVariable("id") Long id) {
        destinationService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/offers")
    public ResponseEntity viewOffers(@RequestParam("destinationId") Long destinationId){
        TravelDestinationModel destination = destinationService.getById(destinationId);
        List<TravelOfferModel> offers = destination.getOffers();
        return new ResponseEntity(offers, HttpStatus.OK);
    }
}
