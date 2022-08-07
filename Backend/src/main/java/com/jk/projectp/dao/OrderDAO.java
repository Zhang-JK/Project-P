package com.jk.projectp.dao;

import com.jk.projectp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {

//    <S extends Order>S save(S entity);
}
