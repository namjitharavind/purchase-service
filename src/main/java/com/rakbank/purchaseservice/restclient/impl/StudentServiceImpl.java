package com.rakbank.purchaseservice.restclient.impl;


import com.rakbank.purchaseservice.core.exception.BusinessException;
import com.rakbank.purchaseservice.restclient.properties.StudentServiceProperties;
import com.rakbank.purchaseservice.restclient.StudentService;
import com.rakbank.purchaseservice.restclient.dto.StudentDto;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private static final String SERVICE_NAME = "student-service";

    @Qualifier("student-service-rest-client")
    private final RestClient restClient;

    private final StudentServiceProperties properties;

    @Retry(name = SERVICE_NAME, fallbackMethod = "getStudentInformationFromCache")
    @Override
    public StudentDto getStudentsById(Long id) {
        log.info("Calling Student Api : {}/{}", properties.getStudentByIdApi(), id);
        StudentDto result = restClient.get()
                .uri(properties.getStudentByIdApi(), id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new BusinessException("2002", HttpStatus.INTERNAL_SERVER_ERROR, "Student Not Found");
                })
                .body(StudentDto.class);
        log.info("Student Api call result: {}", result);
        return result;
    }


    private StudentDto getStudentInformationFromCache(Long id, Exception e) {
        log.error("Fall back method called");
        return new StudentDto();
    }
}
