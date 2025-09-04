package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}

