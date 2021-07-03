package com.bogdanbrl.service;

import com.bogdanbrl.entity.CustomerModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.repository.TravelOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelOfferService {

    @Autowired
    private TravelOfferRepository travelOfferRepository;

    public List<CustomerModel> getCustomer(Long offerId){
        Optional<TravelOfferModel> offerFound = travelOfferRepository.findById(offerId);
        TravelOfferModel travelOfferModel = offerFound.get();
        List<CustomerModel> customerModels = travelOfferModel.getCustomers();

        return customerModels;
    }

    public void addOffer(TravelOfferModel travelOfferModel) {
        travelOfferRepository.save(travelOfferModel);
    }

    public List<TravelOfferModel> findOffers(double maxPrice, long destinationId) {
        return travelOfferRepository.findOffers(maxPrice, destinationId);
    }
}
