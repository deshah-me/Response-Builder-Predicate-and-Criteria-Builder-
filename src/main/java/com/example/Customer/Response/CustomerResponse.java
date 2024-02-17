package com.example.Customer.Response;

import com.example.Customer.Entity.Customer;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private List<Customer> CustomerDetailsList;
    HttpStatus httpStatus;
    String messageType;
    String message;
}
