package com.rakbank.purchaseservice.restclient.dto;


import com.rakbank.purchaseservice.data.enums.Currency;
import com.rakbank.purchaseservice.data.enums.FeeStatus;
import com.rakbank.purchaseservice.restclient.enums.FeeType;
import lombok.*;

import java.time.ZonedDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentFeeDto {
    private Long id;
    private Long studentId;
    private Long feeId;
    private FeeStatus status;
    private Double amount;
    private Double paidAmount;
    private ZonedDateTime paidDate;
    private ZonedDateTime creationDate;
    private String name;
    private FeeType type;
    private Currency currency;
    private ZonedDateTime dueDate;

}
