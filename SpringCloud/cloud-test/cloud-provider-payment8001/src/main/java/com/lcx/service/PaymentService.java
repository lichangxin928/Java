package com.lcx.service;

import com.lcx.domain.Payment;
import com.lcx.domain.ReturnResult;

import java.util.List;

public interface PaymentService {
    List<Payment> findAll();

    Payment getById(Integer id);

    int insertPayment(Payment payment);
}
