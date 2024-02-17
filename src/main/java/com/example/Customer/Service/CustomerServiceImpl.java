package com.example.Customer.Service;

import com.example.Customer.Entity.Customer;
import com.example.Customer.Repository.CustomerRepo;
import com.example.Customer.Response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.*;


@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

private    CustomerRepo customerRepo;


    @Override
    public CustomerResponse getCustomerDetails(Integer customerid) {
        String messageType;
        String message;
        HttpStatus httpStatus;
        List<Customer> customerList = null;
        try {

            customerList = customerRepo.findAll((root, query, criteriaBuilder)
                            -> {
                        List<Predicate> predicates = new ArrayList<>();
                        predicates.add(criteriaBuilder.equal(root.get("customerid"), customerid
                        ));
                        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                    }
            );

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

        return CustomerResponse.builder()
                .message(message)
                .messageType(messageType)
                .httpStatus(httpStatus)
                .CustomerDetailsList(customerList)
                .build();
    }


}