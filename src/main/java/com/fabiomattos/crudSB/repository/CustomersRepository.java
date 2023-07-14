package com.fabiomattos.crudSB.repository;

import com.fabiomattos.crudSB.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository <Customers, Long>{
}
