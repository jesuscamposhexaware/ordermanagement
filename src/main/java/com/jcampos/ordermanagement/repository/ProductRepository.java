package com.jcampos.ordermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jcampos.ordermanagement.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	public Optional<Product> findById(Integer idProduct);

}
