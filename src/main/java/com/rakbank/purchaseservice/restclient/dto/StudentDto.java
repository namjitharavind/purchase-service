package com.rakbank.purchaseservice.restclient.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private String grade;
    private String mobile;
    private String school;
}
