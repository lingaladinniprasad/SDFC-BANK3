package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//public class Account {
//}




@Entity
@Data
@Table(name="Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;
    private String name;
    private String password;
    private String ConfirmPassword;
    private double balance;
    private String address;
    private String mobileNo;

    // Getters and Setters
}
