package com.resale.homeflypayment.repository;

import com.resale.homeflypayment.model.CBankKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CBankKeysRepository extends JpaRepository<CBankKeys, Integer> {

    Optional<CBankKeys> findByBankIdAndProjectId(int bankId, int projectId);

}

