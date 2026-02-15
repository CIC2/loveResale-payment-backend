package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

