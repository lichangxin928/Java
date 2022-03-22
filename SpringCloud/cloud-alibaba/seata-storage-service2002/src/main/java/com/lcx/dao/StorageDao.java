package com.lcx.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    //扣减库存
    void increase(@Param("productId") Long productId, @Param("count") Integer count);
}
