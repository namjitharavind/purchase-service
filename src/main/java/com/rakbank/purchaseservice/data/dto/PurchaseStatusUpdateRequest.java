package com.rakbank.purchaseservice.data.dto;

import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class PurchaseStatusUpdateRequest {

    private PurchaseStatus status;
    private ZonedDateTime paidDate;
}
