package com.customer.managment.customer_managment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  CustomerRepository extends JpaRepository<Customer ,Long>{
	

}
