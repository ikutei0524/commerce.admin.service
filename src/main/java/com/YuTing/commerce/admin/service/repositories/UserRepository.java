package com.YuTing.commerce.admin.service.repositories;

import com.YuTing.commerce.admin.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findById(int id);
}

