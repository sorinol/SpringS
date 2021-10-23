package com.marsor.service;

import com.marsor.entity.FacultyModel;
import com.marsor.entity.UserModel;
import com.marsor.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {

    @InjectMocks
    private FacultyService facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @RepeatedTest(value = 3)
    public void getCustomerFromFacultyTest(){
        FacultyModel facultyModel = new FacultyModel();
        facultyModel.setId(1L);
        facultyModel.setName("Facultate test");

        UserModel customerModel = new UserModel();
        customerModel.setId(1L);
        customerModel.setUsername("director");
//        facultyModel.getUserModelList().add(customerModel);

        Mockito.when(facultyRepository.findById(1L)).thenReturn(Optional.of(facultyModel));
//        List<UserModel> customerModelList = facultyService.getCustomer(1L);

//        Assertions.assertEquals(1, customerModelList.size());
//        UserModel customerFound = customerModelList.get(0);
//        Assertions.assertEquals("director", customerFound.getUsername());
    }
}
