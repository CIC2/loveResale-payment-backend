package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.view.ViewPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewPaymentRepository extends JpaRepository<ViewPayment, Long> {
    List<ViewPayment> findByCustomerIdAndOfferId(
            Integer customerId,
            Integer offerId
    );

    ViewPayment findByPaymentId(Integer paymentId);
}

