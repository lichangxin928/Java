package com.lcx.service;

import com.lcx.domain.User;

public interface UserService {
    User findById(Integer id);

    User findByIdJpa(Integer id);
}
