package com.example.Customer.Service;

import com.example.Customer.Request.CustomerRequest;
import com.example.Customer.Response.CustomerResponse;
import org.springframework.stereotype.Component;

public interface CustomerService {

    CustomerResponse getCustomerDetails(Integer id);

    CustomerResponse deleteCustomerDetails(Integer id);

    public CustomerResponse addCustomerDetails(CustomerRequest customerRequest);

    public CustomerResponse editCustomerDetails(CustomerRequest customerRequest);
}
