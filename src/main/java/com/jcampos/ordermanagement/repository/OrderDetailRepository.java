package com.jcampos.ordermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jcampos.ordermanagement.domain.OrderDetail;
import com.jcampos.ordermanagement.domain.OrderDetailKey;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey>{
	
	@Query(value = "SELECT * FROM orderdetail WHERE id_order=?", nativeQuery=true)
	List<OrderDetail> findByOrderId(Long id);

}
