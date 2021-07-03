package com.bogdanbrl.service;

import com.bogdanbrl.entity.ContinentModel;
import com.bogdanbrl.repository.ContinentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {

    private ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public void addContinent(ContinentModel continentModel){
        continentRepository.save(continentModel);
    }

    public ContinentModel getByName(String name){
        return continentRepository.findByName(name);
    }

    public List<ContinentModel> getContinents(){
        return continentRepository.findAll();
    }

    public ContinentModel getById(Long id){
        return continentRepository.findById(id).get();
    }

    public void editContinent(ContinentModel continentModel){
        continentRepository.save(continentModel);
    }
}
