package com.bogdanbrl.service;

import com.bogdanbrl.entity.CustomerModel;
import com.bogdanbrl.entity.TravelOfferModel;
import com.bogdanbrl.repository.TravelOfferRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TravelOfferServiceTest {

    @InjectMocks
    private TravelOfferService travelOfferService;

    @Mock
    private TravelOfferRepository travelOfferRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @RepeatedTest(value = 3)
    public void getCustomerFromOfferTest(){
        TravelOfferModel travelOfferModel = new TravelOfferModel();
        travelOfferModel.setId(1L);
        travelOfferModel.setTitle("Oferta test");

        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(1L);
        customerModel.setLastName("papagal");
        travelOfferModel.getCustomers().add(customerModel);

        Mockito.when(travelOfferRepository.findById(1L)).thenReturn(Optional.of(travelOfferModel));
        List<CustomerModel> customerModelList = travelOfferService.getCustomer(1L);

        Assertions.assertEquals(1, customerModelList.size());
        CustomerModel customerFound = customerModelList.get(0);
        Assertions.assertEquals("papagal", customerFound.getLastName());
    }
}
