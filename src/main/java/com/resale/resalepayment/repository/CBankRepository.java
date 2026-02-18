package com.resale.resalepayment.repository;

import com.resale.resalepayment.model.CBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CBankRepository extends JpaRepository<CBank, Integer> {
}

