package com.rakbank.purchaseservice.data.dto;

import com.rakbank.purchaseservice.data.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PurchaseFee {

    private Long id;
    private Long feeId;
    private String feeName;
    private Double feeAmount;
    private Currency feeCurrency;
}
