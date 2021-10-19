package com.lcx.dao;

import com.lcx.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<User> userList();
    User selectUserById(int id);
    User selectUserByIdAndName(@Param("id") int id, @Param("name") String name);

}
