package com.resale.resalepayment.repository;

import com.resale.resalepayment.model.BankTransfers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankTransfersRepository extends JpaRepository<BankTransfers, Integer> {
    List<BankTransfers> findAllByOfferIdIn(List<Integer> offerIds);

}

