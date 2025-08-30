package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
