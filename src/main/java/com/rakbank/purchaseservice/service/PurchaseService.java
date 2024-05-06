package com.rakbank.purchaseservice.service;

import com.rakbank.purchaseservice.data.dto.Purchase;
import com.rakbank.purchaseservice.data.dto.PurchaseRequest;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {

    Purchase create(PurchaseRequest purchaseRequestDto);

    List<Purchase> findPurchaseByStudentId(Long studentId);


    Optional<Purchase> findPurchaseById(String id);

    Optional<Purchase> updateCustomAmount(Double customAmount, String purchaseId);

    Optional<Purchase> updatePurchaseStatus(String status, String purchaseId);
}
