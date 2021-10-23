package com.marsor.service;

import com.marsor.entity.FacultyModel;
import com.marsor.entity.UserModel;
import com.marsor.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    public List<UserModel> getCustomer(Long facultyId){
        Optional<FacultyModel> facultyFound = facultyRepository.findById(facultyId);
        FacultyModel facultyModel = facultyFound.get();
        List<UserModel> customerModels = facultyModel.getUserModelList();

        return customerModels;
    }

    public void addFaculty(FacultyModel facultyModel) {
        facultyRepository.save(facultyModel);
    }

    public void editFaculty(FacultyModel facultyModel) {
        facultyRepository.save(facultyModel);
    }

    public List<FacultyModel> findFaculties(long universityId) {
        return facultyRepository.findFaculties(universityId);
    }

    public FacultyModel getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public void deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
    }

    public List<FacultyModel> getFaculties(){
        return facultyRepository.findAll();
    }

}
