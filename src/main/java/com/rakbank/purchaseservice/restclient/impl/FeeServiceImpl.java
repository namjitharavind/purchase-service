package com.rakbank.purchaseservice.restclient.impl;

import com.rakbank.purchaseservice.core.exception.BusinessException;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeStatusUpdateRequest;
import com.rakbank.purchaseservice.restclient.properties.FeeServiceProperties;
import com.rakbank.purchaseservice.restclient.FeeService;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeDto;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private static final String SERVICE_NAME = "fee-service";

    @Qualifier("fee-service-rest-client")
    private final RestClient restClient;

    private final FeeServiceProperties properties;


    @Retry(name = SERVICE_NAME, fallbackMethod = "getStudentFeeFromCache")
    @Override
    public StudentFeeDto getFeeById(Long id) {
        log.info("Calling Fee Api : {}/{}", properties.getStudentFeeApi(), id);
        StudentFeeDto result = restClient.get()
                .uri(properties.getStudentFeeApi(), id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new BusinessException("2002", HttpStatus.INTERNAL_SERVER_ERROR, "Student Not Found");
                })
                .body(StudentFeeDto.class);
        log.info("Fee Api call result: {}", result);
        return result;

    }

    private StudentFeeDto getStudentFeeFromCache(Long id, Exception e) {
        return new StudentFeeDto();
    }


    @Retry(name = SERVICE_NAME, fallbackMethod = "updatePurchaseStatusFallBack")
    @Override
    public void updateStudentFeeStatusAndAmount(StudentFeeStatusUpdateRequest request) {
        log.info("Calling Student Fee Service for payment status update: {}", properties.getStudentFeeStatusUpdateApi());
        ResponseEntity<Void> response = restClient.post()
                .uri(properties.getStudentFeeStatusUpdateApi())
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();
        log.info("Student Fee Service Api call result: {}", response);
    }

    private void updatePurchaseStatusFallBack(StudentFeeStatusUpdateRequest request, Exception e) {
        log.error("Pushed to a dead letter queue for later call back");
    }
}
