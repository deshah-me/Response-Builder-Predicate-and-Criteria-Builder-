package com.example.Customer.Controller;


import com.example.Customer.Repository.CustomerRepo;
import com.example.Customer.Response.CustomerResponse;
import com.example.Customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
@Autowired
  private  CustomerService customerService;

@GetMapping("/details/{customerid}")
    public ResponseEntity<CustomerResponse> CustomerDetails(@PathVariable  Integer customerid){
    CustomerResponse response= customerService.getCustomerDetails(customerid);
return  new ResponseEntity<>(response,response.getHttpStatus());
}

}
