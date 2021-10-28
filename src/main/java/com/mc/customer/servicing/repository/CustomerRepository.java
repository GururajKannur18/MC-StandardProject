package com.mc.customer.servicing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mc.customer.servicing.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
