package com.rakbank.purchaseservice.restclient;

import com.rakbank.purchaseservice.restclient.dto.StudentFeeDto;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeStatusUpdateRequest;

public interface FeeService {
    StudentFeeDto getFeeById(Long id);
    void updateStudentFeeStatusAndAmount(StudentFeeStatusUpdateRequest request);
}
