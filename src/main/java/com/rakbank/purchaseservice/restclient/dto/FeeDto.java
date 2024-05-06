package com.rakbank.purchaseservice.restclient.dto;


import com.rakbank.purchaseservice.restclient.enums.FeeType;
import lombok.*;

import java.time.ZonedDateTime;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeeDto {
    private Long id;
    private String name;
    private FeeType type;
    private String currency;
    private Double amount;
    private ZonedDateTime creationDate;
    private ZonedDateTime dueDate;
    private Double paidAmount;
    private ZonedDateTime paidDate;


}
