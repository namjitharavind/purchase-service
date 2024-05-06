package com.rakbank.purchaseservice.restclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class StudentFeeStatusUpdateRequest {
    private Long studentId;
    private Double totalAmount;
    private Double paidAmount;
    private ZonedDateTime paidDate;
    List<StudentFeeForUpdate> studentFees;
}
