package com.bogdanbrl.service;

import com.bogdanbrl.entity.ContinentModel;
import com.bogdanbrl.entity.CountryModel;
import com.bogdanbrl.entity.TravelDestinationModel;
import com.bogdanbrl.repository.ContinentRepository;
import com.bogdanbrl.repository.CountryRepository;
import com.bogdanbrl.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ContinentRepository continentRepository;

    public List<TravelDestinationModel> getAll(){
        List<TravelDestinationModel> travelList = destinationRepository.findAll();
        return travelList;
    }

    public void addDestination(TravelDestinationModel travelDestinationModel){

        ContinentModel continent = travelDestinationModel.getCountry().getContinent();
        ContinentModel continentSearch = continentRepository.findByName(continent.getName());
        if (continentSearch == null) {
            continentRepository.save(continent);
            continentSearch = continentRepository.findByName(continent.getName());
        }

        CountryModel country = travelDestinationModel.getCountry();
        CountryModel countrySearch = countryRepository.findByName(country.getName());
        if (countrySearch == null) {
            country.setContinent(continentSearch);
            countryRepository.save(country);
            countrySearch = countryRepository.findByName(country.getName());
        }

        countrySearch.setContinent(continentSearch);
        countryRepository.save(countrySearch);

        travelDestinationModel.setCountry(countrySearch);

        destinationRepository.save(travelDestinationModel);
    }

    public TravelDestinationModel getById(Long id) {
        Optional<TravelDestinationModel> destinationOptional = destinationRepository.findById(id);

        return destinationOptional.get();
    }

    public void editDestination(TravelDestinationModel travelDestinationModel) {
        ContinentModel continent = travelDestinationModel.getCountry().getContinent();
        ContinentModel continentSearch = continentRepository.findByName(continent.getName());
        if (continentSearch == null) {
            continentRepository.save(continent);
            continentSearch = continentRepository.findByName(continent.getName());
        }

        CountryModel country = travelDestinationModel.getCountry();
        CountryModel countrySearch = countryRepository.findByName(country.getName());
        if (countrySearch == null) {
            country.setContinent(continentSearch);
            countryRepository.save(country);
            countrySearch = countryRepository.findByName(country.getName());
        }

        countrySearch.setContinent(continentSearch);
        countryRepository.save(countrySearch);

        travelDestinationModel.setCountry(countrySearch);

        destinationRepository.save(travelDestinationModel);
    }

    public void remove(Long id) {
        destinationRepository.deleteById(id);
    }
}
