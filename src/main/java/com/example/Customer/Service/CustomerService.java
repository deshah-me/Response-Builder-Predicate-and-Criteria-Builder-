package com.example.Customer.Service;

import com.example.Customer.Response.CustomerResponse;
import org.springframework.stereotype.Component;

public interface CustomerService {

     CustomerResponse getCustomerDetails(Integer id) ;

}
