package com.jcampos.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jcampos.ordermanagement.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Long, Order>{

}
