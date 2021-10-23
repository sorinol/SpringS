package com.marsor.service;

import com.marsor.entity.CityModel;
import com.marsor.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void addCity(CityModel cityModel){
        cityRepository.save(cityModel);
    }
    public List<CityModel> getCities(){
        return cityRepository.findAll();
    }
    public void editCity(CityModel cityModel){
        cityRepository.save(cityModel);
    }
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }


    public CityModel getById(Long id){
        return cityRepository.findById(id).get();
    }

    public CityModel getByName(String name){
        return cityRepository.findByName(name);
    }




}

