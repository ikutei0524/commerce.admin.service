package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
