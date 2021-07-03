package com.bogdanbrl.controller;

import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.service.DestinationService;
import com.bogdanbrl.service.TravelOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private TravelOfferService offerService;


    @GetMapping("/searchOffer")
    public ResponseEntity searchOffer(@RequestParam("maxPrice") Double maxPrice, @RequestParam("destinationId") Long destinationId) {
        List<TravelOfferModel> offers = offerService.findOffers(maxPrice, destinationId);
        return new ResponseEntity(offers, HttpStatus.OK);
    }
}
