package com.bogdanbrl.service;

import com.bogdanbrl.entity.CustomerModel;
import com.bogdanbrl.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void addCustomer(CustomerModel customerModel){
        this.customerRepository.save(customerModel);
    }

    public void editCustomer(CustomerModel customerModel){
        this.customerRepository.save(customerModel);
    }

    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }

    public List<CustomerModel> getCustomers () {
        return customerRepository.findAll();
    }

    public CustomerModel getCustomerById (Long id){
        return customerRepository.findById(id).orElse(null);
    }
}
