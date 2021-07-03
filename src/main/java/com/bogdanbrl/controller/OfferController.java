package com.bogdanbrl.controller;

import com.bogdanbrl.entity.CustomerModel;
import com.bogdanbrl.entity.TravelDestinationModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.service.TravelOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OfferController {

    @Autowired
    private TravelOfferService offerService;

    @GetMapping("reservations")
    public String getReservations(@RequestParam("id") Long id, Model model) {
        List<CustomerModel> customers = offerService.getCustomer(id);
        model.addAttribute("customers", customers);

        return "client-page";
    }

    @GetMapping("addOfferPage")
    public String addOfferPage(@RequestParam("id") Long id, Model model){

        TravelOfferModel travelOfferModel = new TravelOfferModel();
        TravelDestinationModel travelDestinationModel = new TravelDestinationModel();
        travelDestinationModel.setId(id);

        travelOfferModel.setDestination(travelDestinationModel);

        model.addAttribute("newOffer", travelOfferModel);
        return "add-offer-page";
    }

    @PostMapping("addOffer")
    public String addOffer(@ModelAttribute TravelOfferModel travelOfferModel){
        offerService.addOffer(travelOfferModel);
        return "redirect:/getDestinations";
    }
}
