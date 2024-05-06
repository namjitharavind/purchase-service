package com.rakbank.purchaseservice.service.impl;

import com.rakbank.purchaseservice.data.dto.Purchase;
import com.rakbank.purchaseservice.data.dto.PurchaseRequest;

import com.rakbank.purchaseservice.data.entity.PurchaseEntity;
import com.rakbank.purchaseservice.data.entity.PurchaseFeeEntity;
import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import com.rakbank.purchaseservice.data.mapper.PurchaseMapper;
import com.rakbank.purchaseservice.repository.PurchaseRepository;
import com.rakbank.purchaseservice.restclient.FeeService;
import com.rakbank.purchaseservice.restclient.StudentService;
import com.rakbank.purchaseservice.restclient.dto.StudentDto;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeForUpdate;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeDto;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeStatusUpdateRequest;
import com.rakbank.purchaseservice.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final StudentService studentService;
    private final FeeService feeService;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public Purchase create(PurchaseRequest purchaseRequestDto) {

        StudentDto student = studentService.getStudentsById(purchaseRequestDto.getStudentId());
        List<PurchaseFeeEntity> purchaseFees = purchaseRequestDto.getPurchaseFees().stream().map(fee -> {
            StudentFeeDto feeDto = feeService.getFeeById(fee.getFeeId());
            return purchaseMapper.mapToPurchaseFee(feeDto);
        }).toList();

        PurchaseEntity purchase = purchaseMapper.mapToPurchase(student, purchaseRequestDto, purchaseFees, PurchaseStatus.CREATED);

        List<PurchaseFeeEntity> purchaseFeesList = purchaseFees.stream().peek(p -> p.setPurchase(purchase)).toList();
        purchase.setPurchaseFees(purchaseFeesList);

        PurchaseEntity purchaseResult = purchaseRepository.saveAndFlush(purchase);

        return purchaseMapper.mapToPurchaseDto(purchaseResult);
    }

    @Override
    public List<Purchase> findPurchaseByStudentId(Long studentId) {

        return purchaseRepository.findByStudentId(studentId).stream().map(purchaseMapper::mapToPurchaseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Purchase> findPurchaseById(String id) {

        return purchaseRepository.findById(id).map(purchaseMapper::mapToPurchaseDto);
    }

    @Override
    public Optional<Purchase> updateCustomAmount(Double customAmount, String purchaseId) {
        purchaseRepository.updateCustomAmountByPurchaseId(customAmount, purchaseId);
        return purchaseRepository.findById(purchaseId).map(purchaseMapper::mapToPurchaseDto);
    }

    @Override
    public Optional<Purchase> updatePurchaseStatus(String status, String purchaseId) {
        purchaseRepository.findById(purchaseId).ifPresent(p->{
            purchaseRepository.updatePurchaseStatus(PurchaseStatus.valueOf(status), ZonedDateTime.now(), purchaseId);
            StudentFeeStatusUpdateRequest statusUpdateRequest=new StudentFeeStatusUpdateRequest();
            statusUpdateRequest.setStudentId(p.getStudentId());
            statusUpdateRequest.setTotalAmount(p.getTotalAmount());
            statusUpdateRequest.setPaidAmount(p.getCustomAmount());
            List<StudentFeeForUpdate> studentFees=p.getPurchaseFees().stream().map(pp->new StudentFeeForUpdate(pp.getFeeId())).toList();
            statusUpdateRequest.setStudentFees(studentFees);
            feeService.updateStudentFeeStatusAndAmount(statusUpdateRequest);
        });
        return purchaseRepository.findById(purchaseId).map(purchaseMapper::mapToPurchaseDto);
    }
}
