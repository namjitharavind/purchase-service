package com.rakbank.purchaseservice.data.entity;

import com.rakbank.purchaseservice.data.enums.Currency;
import com.rakbank.purchaseservice.data.enums.PaymentMethodType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethod {

    private PaymentMethodType paymentMethodType;
    private String name;
    private Currency currency;

}
