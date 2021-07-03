package com.bogdanbrl.controller;

import com.bogdanbrl.entity.TravelDestinationModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.service.ContinentService;
import com.bogdanbrl.service.CountryService;
import com.bogdanbrl.service.DestinationService;
import com.bogdanbrl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @GetMapping("getDestinations")
    public String getDestinations(Model model) {

        List<TravelDestinationModel> destinations = destinationService.getAll();

        model.addAttribute("title", "Titlul paginii");
        model.addAttribute("phoneNumber", "025655664665");

        model.addAttribute("destinations", destinations);
        model.addAttribute("currentUser", userService.getCurrentUser().getUsername());

        return "travel-page";
    }

    @GetMapping("addDestinationPage")
    public String getAddDestinationPage(Model model) {
        model.addAttribute("destination", new TravelDestinationModel());
        model.addAttribute("continentsList", continentService.getContinents());
        model.addAttribute("countryList", countryService.getCountries());
        return "add-destination-page";
    }

    @PostMapping("addDestination")
    public String addDestination(@ModelAttribute TravelDestinationModel travelDestinationModel) {
        destinationService.addDestination(travelDestinationModel);
        return "redirect:/getDestinations";
    }

    @GetMapping("editDestinationPage")
    public String getEditDestinationPage(@RequestParam("id") Long id, Model model) {
        TravelDestinationModel travelDestinationModel = destinationService.getById(id);
        model.addAttribute("destination", travelDestinationModel);
        model.addAttribute("continentsList", continentService.getContinents());
        return "edit-destination-page";
    }

    @PostMapping("editDestination")
    public String editDestination(@ModelAttribute TravelDestinationModel travelDestinationModel) {
        destinationService.editDestination(travelDestinationModel);
        return "redirect:/getDestinations";
    }

    @GetMapping("deletedestination")
    public String deleteDestination(@RequestParam("id") Long id, Model model) {
        destinationService.remove(id);
        return "redirect:/getDestinations";
    }

    @GetMapping("offers")
    public String viewOffers(@RequestParam("id") Long id, Model model){
        TravelDestinationModel destination = destinationService.getById(id);
        List<TravelOfferModel> offers = destination.getOffers();
        model.addAttribute("offers", offers);
        return "view-offers-page";
    }
}
