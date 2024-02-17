package com.example.Customer.Controller;


import com.example.Customer.Repository.CustomerRepo;
import com.example.Customer.Request.CustomerRequest;
import com.example.Customer.Response.CustomerResponse;
import com.example.Customer.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
@Autowired
  private  CustomerService customerService;

    @Operation(summary = "Get a customer by its id")

@GetMapping("/details/{customerid}")
    public ResponseEntity<CustomerResponse> CustomerDetails(@PathVariable  Integer customerid){
    CustomerResponse response= customerService.getCustomerDetails(customerid);
return  new ResponseEntity<>(response,response.getHttpStatus());
}

    @Operation(summary = "Delete a customer by its id",description = "Delete Operation",tags = "Deleting Data")
    @DeleteMapping("/details/{customerid}")
    public ResponseEntity<CustomerResponse> DeleteCustomer(@PathVariable  Integer customerid){
        CustomerResponse response= customerService.deleteCustomerDetails(customerid);
        return  new ResponseEntity<>(response,response.getHttpStatus());
    }


    @Operation(summary = "Create a customer",description = "Create Operation",tags = "Create Data")
    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> CreateCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse response=customerService.addCustomerDetails(customerRequest);
        return  new ResponseEntity<>(response,response.getHttpStatus());
    }

    @Operation(summary = "Update a customer",description = "Update customer",tags = "Update Data")
    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> UpdateCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse response=customerService.editCustomerDetails(customerRequest);
        return  new ResponseEntity<>(response,response.getHttpStatus());
    }


}
