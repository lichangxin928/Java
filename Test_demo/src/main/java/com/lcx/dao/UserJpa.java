package com.lcx.dao;

import com.lcx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJpa extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
}