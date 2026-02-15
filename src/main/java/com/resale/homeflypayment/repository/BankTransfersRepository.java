package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.BankTransfers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransfersRepository extends JpaRepository<BankTransfers, Integer> {
    List<BankTransfers> findAllByOfferIdIn(List<Integer> offerIds);

}

