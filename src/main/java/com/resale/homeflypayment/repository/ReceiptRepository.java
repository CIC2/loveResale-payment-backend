package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Optional<Receipt> findByPaymentId(Integer integer);
}

