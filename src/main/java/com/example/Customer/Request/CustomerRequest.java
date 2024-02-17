package com.example.Customer.Request;


import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {
    private Integer customerid;
    private Integer age;
    private String country;
    private String customer_last_name;
    private String customer_name;
    private Integer phone;
}
