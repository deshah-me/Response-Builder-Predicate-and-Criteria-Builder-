package com.example.Customer.Builder;

import com.example.Customer.Entity.Customer;
import com.example.Customer.Request.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("customResponseBuilder")
@Slf4j
public class ResponseBuilder {

    public List<CustomerRequest> getCustomerDetails(List<Customer> customerListEntity){
        List<CustomerRequest> customerRequestList;
        customerRequestList= customerListEntity.stream().map(
                CustomerListDetails->{
                    return CustomerRequest
                            .builder()
                            .customer_name(CustomerListDetails.getCustomer_last_name())
                            .customerid(CustomerListDetails.getCustomerid())
                            .age(CustomerListDetails.getAge())
                            .customer_last_name(CustomerListDetails.getCustomer_last_name())
                            .phone(CustomerListDetails.getPhone())
                            .country(CustomerListDetails.getCountry()
                            ) .build();
                }
        ).collect(Collectors.toList());
                    return customerRequestList;
    }
}