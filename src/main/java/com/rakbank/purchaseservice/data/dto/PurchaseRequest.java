package com.rakbank.purchaseservice.data.dto;


import com.rakbank.purchaseservice.data.enums.Currency;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class PurchaseRequest {
    private Long studentId;
    private Double customAmount;
    private Currency currency;
    List<PurchaseFeeRequest> purchaseFees;
}
