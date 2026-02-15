package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.CBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CBankRepository extends JpaRepository<CBank, Integer> {
}

