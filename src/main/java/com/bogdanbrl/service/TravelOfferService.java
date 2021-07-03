package com.bogdanbrl.service;

import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.entity.UserModel;
import com.bogdanbrl.repository.TravelOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelOfferService {

    @Autowired
    private TravelOfferRepository travelOfferRepository;

    public List<UserModel> getCustomer(Long offerId){
        Optional<TravelOfferModel> offerFound = travelOfferRepository.findById(offerId);
        TravelOfferModel travelOfferModel = offerFound.get();
        List<UserModel> customerModels = travelOfferModel.getCustomers();

        return customerModels;
    }

    public void addOffer(TravelOfferModel travelOfferModel) {
        travelOfferRepository.save(travelOfferModel);
    }

    public void editOffer(TravelOfferModel travelOfferModel) {
        travelOfferRepository.save(travelOfferModel);
    }

    public List<TravelOfferModel> findOffers(double maxPrice, long destinationId) {
        return travelOfferRepository.findOffers(maxPrice, destinationId);
    }

    public TravelOfferModel getOfferById(Long id) {
        return travelOfferRepository.findById(id).orElse(null);
    }

    public void deleteOfferById (Long id) {
        travelOfferRepository.deleteById(id);
    }

    public List<TravelOfferModel> getOffers (){
        return travelOfferRepository.findAll();
    }

}
