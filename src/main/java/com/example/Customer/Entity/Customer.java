package com.example.Customer.Entity;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "Customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customerid")
    private Integer customerid;
    @Column(name = "age")
    private Integer age;
    @Column(name = "country")
    private String country;
    @Column(name = "customer_last_name")
    private String customer_last_name;
    @Column(name = "customer_name")
    private String customer_name;
    @Column(name = "phone")
    private Integer phone;
}
