package com.lcx.service;

import com.lcx.dao.PaymentDao;
import com.lcx.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao dao;

    @Override
    public List<Payment> findAll() {
        List<Payment> all = dao.findAll();
        return all;
    }

    @Override
    public Payment getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public int insertPayment(Payment payment) {
        return dao.insertStudent(payment);
    }
}
