package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Account;

//public interface AccountRepository {
//}
//package com.sdfc.bank.repository;



public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(Long accountNumber);
    Account findByName(String name);
}
