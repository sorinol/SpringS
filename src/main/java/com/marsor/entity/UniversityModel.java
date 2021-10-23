package com.marsor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "universities")
public class UniversityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private CityModel city;

    @JsonIgnore
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<FacultyModel> facultyModelList= new ArrayList<>();

    public List<FacultyModel> getFacultyModelList() {
        return facultyModelList;
    }

    public void setFacultyModelList(List<FacultyModel> facultyModelList) {
        this.facultyModelList = facultyModelList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }


}
