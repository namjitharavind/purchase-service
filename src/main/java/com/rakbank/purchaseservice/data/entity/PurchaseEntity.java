package com.rakbank.purchaseservice.data.entity;

import com.rakbank.purchaseservice.data.enums.Currency;
import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name ="student_id")
    private Long studentId;

    @Column(name ="student_name")
    private String studentName;

    @Column(name ="school_name")
    private String schoolName;

    @Column(name ="total_amount")
    private Double totalAmount;

    @Column(name ="custom_amount")
    private Double customAmount;

    @Column(name ="currency")
    private Currency currency;

    @Column(name ="creation_date")
    private ZonedDateTime creationDate;

    @Column(name ="paid_date")
    private ZonedDateTime paidDate;

    @Column(name ="status")
    private PurchaseStatus status;


    @OneToMany(mappedBy = "purchase",cascade = CascadeType.ALL)
    private List<PurchaseFeeEntity> purchaseFees;
}
