package com.example.Customer.Service;

import com.example.Customer.Entity.Customer;
import com.example.Customer.Repository.CustomerRepo;
import com.example.Customer.Request.CustomerRequest;
import com.example.Customer.Response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.criteria.*;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    private CustomerRepo customerRepo;


    @Override
    public CustomerResponse addCustomerDetails(CustomerRequest customerRequest) {
        String messageType;
        String message;
        HttpStatus httpStatus;
        List<Customer> customerList = null;
        try {
            Optional<Customer> customer = customerRepo.findById(customerRequest.getCustomerid());
            if (customer.isEmpty()) {
                Customer customerdetails = new Customer();
                customerdetails.setCustomer_name(customerRequest.getCustomer_name());
                customerdetails.setCustomer_last_name(customerRequest.getCustomer_last_name());
                customerdetails.setCountry(customerRequest.getCountry());
                customerdetails.setPhone(customerRequest.getPhone());
                customerdetails.setAge(customerRequest.getAge());
                customerRepo.save(customerdetails);

                messageType = "Success";
                httpStatus = HttpStatus.OK;
                message = "Suceess";
            } else {
                messageType = "Failure";
                httpStatus = HttpStatus.OK;
                message = "Data Already Exist";

            }

        } catch (Exception ex) {
            messageType = "Failure";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Failure";
        }

        return CustomerResponse.builder().message(message).messageType(messageType).httpStatus(httpStatus).CustomerDetailsList(customerList).build();
    }

    @Override
    public CustomerResponse editCustomerDetails(CustomerRequest customerRequest) {
        String messageType;
        String message;
        HttpStatus httpStatus;
        List<Customer> customerList = null;

        try {
            Optional<Customer> existingCustomer = customerRepo.findById(customerRequest.getCustomerid());
            if (existingCustomer.isPresent()) {
                Customer customer = existingCustomer.get();
                customer.setCustomer_name(customerRequest.getCustomer_name());
                customer.setCustomer_last_name(customerRequest.getCustomer_last_name());
                customer.setCountry(customerRequest.getCountry());
                customer.setPhone(customerRequest.getPhone());
                customer.setAge(customerRequest.getAge());

                Customer updatedCustomer = customerRepo.save(customer);

                messageType = "Success";
                httpStatus = HttpStatus.OK;
                message = "Customer updated successfully";
                customerList = Collections.singletonList(updatedCustomer);
            } else {
                messageType = "Failure";
                httpStatus = HttpStatus.NOT_FOUND;
                message = "Customer not found";
            }
        } catch (Exception ex) {
            messageType = "Failure";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while updating the customer";
        }

        return CustomerResponse.builder()
                .message(message)
                .messageType(messageType)
                .httpStatus(httpStatus)
                .CustomerDetailsList(customerList)
                .build();
    }
    @Override
    public CustomerResponse getCustomerDetails(Integer customerid) {
        String messageType;
        String message;
        HttpStatus httpStatus;
        List<Customer> customerList = null;
        try {

            customerList = customerRepo.findAll((root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("customerid"), customerid));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            });

            if (!CollectionUtils.isEmpty(customerList)) {
                messageType = "Success";
                httpStatus = HttpStatus.OK;
                message = "Suceess";
            } else {
                messageType = "Failure";
                httpStatus = HttpStatus.OK;
                message = "Failure";
            }
        } catch (Exception ex) {
            messageType = "Failure";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Failure";
        }

        return CustomerResponse.builder().message(message).messageType(messageType).httpStatus(httpStatus).CustomerDetailsList(customerList).build();
    }


    @Override
    public CustomerResponse deleteCustomerDetails(Integer customerid) {
        String messageType;
        String message;
        HttpStatus httpStatus;
        List<Customer> customerList = null;
        try {
            customerRepo.deleteById(customerid);
            messageType = "Success";
            httpStatus = HttpStatus.OK;
            message = "Suceess";
        } catch (Exception ex) {
            messageType = "Failure";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Failure";
        }

        return CustomerResponse.builder().message(message).messageType(messageType).httpStatus(httpStatus).CustomerDetailsList(customerList).build();
    }


}