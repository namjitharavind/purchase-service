package com.rakbank.purchaseservice.restclient;



import com.rakbank.purchaseservice.restclient.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto getStudentsById(Long id);

}
