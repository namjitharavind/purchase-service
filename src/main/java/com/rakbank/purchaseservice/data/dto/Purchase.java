package com.rakbank.purchaseservice.data.dto;


import com.rakbank.purchaseservice.data.enums.Currency;
import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import java.util.List;


@Getter
@Setter
public class Purchase {
    private String id;
    private Long studentId;
    private String studentName;
    private String schoolName;
    private Double totalAmount;
    private Double customAmount;
    private Currency currency;
    private ZonedDateTime creationDate;
    private ZonedDateTime paidDate;
    private PurchaseStatus status;
    private List<PurchaseFee> purchaseFees;
}
