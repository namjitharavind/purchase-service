package com.rakbank.purchaseservice.data.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
public class PurchasedStudentFee {
    private Long id;
    private Long feeId;
    private Double pendingAmount;
    private Double paidAmount;
    private Date paidDate;
    private ZonedDateTime creationDate;
}
