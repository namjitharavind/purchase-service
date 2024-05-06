package com.rakbank.purchaseservice.data.entity;

import com.rakbank.purchaseservice.data.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase_fee")
public class PurchaseFeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="fee_id")
    private Long feeId;

    @Column(name ="fee_name")
    private String feeName;

    @Column(name ="fee_amount")
    private Double feeAmount;

    @Column(name ="currency")
    private Currency feeCurrency;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchase;

}
