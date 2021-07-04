package com.bogdanbrl.controller;

import com.bogdanbrl.dto.BuyOfferDto;
import com.bogdanbrl.dto.OfferDto;
import com.bogdanbrl.entity.TravelDestinationModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.entity.UserModel;
import com.bogdanbrl.service.DestinationService;
import com.bogdanbrl.service.TravelOfferService;
import com.bogdanbrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TravelOfferService offerService;

    @Autowired
    private UserService userService;

    @GetMapping("/offers/reservations")
    public ResponseEntity getReservations(@RequestParam("id") Long id) {
        List<UserModel> customers = offerService.getCustomer(id);
        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @PostMapping("/offers/addOffer")
    public ResponseEntity addOffer(@RequestBody OfferDto offerDto) {
        TravelDestinationModel destinationModel = destinationService.getById(offerDto.getDestinationId());
        if (destinationModel == null) {
            return new ResponseEntity("Destinatie invalida!", HttpStatus.BAD_REQUEST);
        }
        TravelOfferModel offerModel = new TravelOfferModel();
        offerModel.setTitle(offerDto.getTitle());
        offerModel.setDescription(offerDto.getDescription());
        offerModel.setContactNumber(offerDto.getContactNumber());
        offerModel.setPricePerNight(offerDto.getPricePerNight());
        offerModel.setDestination(destinationModel);
        offerModel.setImageUrl(offerDto.getImageUrl());

        offerService.addOffer(offerModel);
        return new ResponseEntity(offerModel, HttpStatus.OK);
    }

    @PutMapping("/offers/editOffer/{id}")
    public ResponseEntity editOffer(@PathVariable("id") Long id, @RequestBody OfferDto offerDto) {

        TravelOfferModel offerModel = offerService.getOfferById(id);
        if (offerModel == null) {
            return new ResponseEntity(" Oferta nu exista", HttpStatus.BAD_REQUEST);
        }

        TravelDestinationModel destinationModel = destinationService.getById(offerDto.getDestinationId());
        if (destinationModel == null) {
            return new ResponseEntity("Destinatie invalida!", HttpStatus.BAD_REQUEST);
        }

        offerModel.setTitle(offerDto.getTitle());
        offerModel.setDescription(offerDto.getDescription());
        offerModel.setContactNumber(offerDto.getContactNumber());
        offerModel.setPricePerNight(offerDto.getPricePerNight());
        offerModel.setDestination(destinationModel);
        offerModel.setImageUrl(offerDto.getImageUrl());

        offerService.editOffer(offerModel);
        return new ResponseEntity(offerModel, HttpStatus.OK);
    }

    @DeleteMapping("/offers/deleteOffer/{id}")
    public ResponseEntity deleteOfferById(@PathVariable("id") Long id){
        offerService.deleteOfferById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/offers/getOffers")
    public ResponseEntity getOffers() {
        List<TravelOfferModel> offers = offerService.getOffers();
        return new ResponseEntity(offers, HttpStatus.OK);
    }

    @GetMapping("/offers/getOfferById/{id}")
    public ResponseEntity getOfferById (@PathVariable("id") Long id) {
        TravelOfferModel offer = offerService.getOfferById(id);
        if (offer == null) {
            return new ResponseEntity("oferta nu exista", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(offer, HttpStatus.OK);
    }

    @PostMapping("/offers/buyOffer")
    public ResponseEntity buyOffer(@RequestBody BuyOfferDto buyOfferDto){
        TravelOfferModel offerModel = offerService.getOfferById(buyOfferDto.getOfferId());
        List<UserModel> customers = offerModel.getCustomers();

        UserModel userModel = userService.getUserById(buyOfferDto.getUserId());
        if (userModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        customers.add(userModel);
        offerService.editOffer(offerModel);

        return new ResponseEntity(offerModel, HttpStatus.OK);
    }
}
