package com.marsor.service;

import com.marsor.entity.CityModel;
import com.marsor.entity.UniversityModel;
import com.marsor.repository.CityRepository;
import com.marsor.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private CityRepository cityRepository;


    public List<UniversityModel> getAll(){
        List<UniversityModel> travelList = universityRepository.findAll();
        return travelList;
    }

    public void addUniversity(UniversityModel universityModel){

        CityModel city = universityModel.getCity();
        CityModel citySearch = cityRepository.findByName(city.getName());
        if (citySearch == null) {
            universityModel.setCity(citySearch);
            cityRepository.save(city);
            citySearch = cityRepository.findByName(city.getName());
        }
        cityRepository.save(citySearch);
        universityModel.setCity(citySearch);
        universityRepository.save(universityModel);
    }

    public UniversityModel getById(Long id){
        Optional<UniversityModel> universityOptional = universityRepository.findById(id);

        return universityOptional.get();
    }

    public void editUniversity(UniversityModel universityModel) {

        CityModel city = universityModel.getCity();
        CityModel citySearch = cityRepository.findByName(city.getName());
        if (citySearch == null) {
            cityRepository.save(city);
            citySearch = cityRepository.findByName(city.getName());
        }
        cityRepository.save(citySearch);
        universityModel.setCity(citySearch);
        universityRepository.save(universityModel);
    }

    public void remove(Long id) {
        universityRepository.deleteById(id);
    }
}
