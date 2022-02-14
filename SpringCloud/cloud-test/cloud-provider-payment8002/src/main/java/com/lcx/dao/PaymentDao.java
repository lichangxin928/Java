package com.lcx.dao;

import com.lcx.domain.Payment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaymentDao {

    @Select("select * from payment")
    List<Payment> findAll();

    @Select("select * from payment where id = #{id}")
    Payment getById(Integer id);

    @Insert("insert into payment values(#{id},#{serial})")
    int insertStudent(Payment payment);
}
