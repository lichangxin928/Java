package com.lcx.service.impl;

import com.lcx.dao.StorageDao;
import com.lcx.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao stroageDao;

    @Override
    public void increase(Long productId, Integer count) {
        stroageDao.increase(productId,count);
    }
}
