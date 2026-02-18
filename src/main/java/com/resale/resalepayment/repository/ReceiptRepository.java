package com.resale.resalepayment.repository;

import com.resale.resalepayment.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    Optional<Receipt> findByPaymentId(Integer integer);
}

