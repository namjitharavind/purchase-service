package com.rakbank.purchaseservice.data.mapper;


import com.rakbank.purchaseservice.data.dto.Purchase;
import com.rakbank.purchaseservice.data.dto.PurchaseFeeRequest;
import com.rakbank.purchaseservice.data.dto.PurchaseRequest;
import com.rakbank.purchaseservice.data.entity.PurchaseEntity;
import com.rakbank.purchaseservice.data.entity.PurchaseFeeEntity;
import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import com.rakbank.purchaseservice.restclient.dto.StudentDto;
import com.rakbank.purchaseservice.restclient.dto.StudentFeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(config = CommonMapperConfig.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,imports = {UUID.class, ZonedDateTime.class})
public interface PurchaseMapper {
    PurchaseMapper MAPPER = Mappers.getMapper(PurchaseMapper.class);

    @Mapping(source = "studentSource.id", target = "studentId")
    @Mapping(source = "studentSource.name", target = "studentName")
    @Mapping(source = "studentSource.school", target = "schoolName")
    @Mapping(source = "purchaseFeesSource", target = "totalAmount", qualifiedByName = "calculateTotalAmount")
    @Mapping(source = "purchaseFeesSource", target = "customAmount", qualifiedByName = "calculateTotalAmount")
    @Mapping(source = "purchaseSource.currency", target = "currency")
    @Mapping(target = "creationDate", expression = "java(ZonedDateTime.now())")
    @Mapping(source = "purchaseStatus",target = "status")
    PurchaseEntity mapToPurchase(StudentDto studentSource, PurchaseRequest purchaseSource, List<PurchaseFeeEntity> purchaseFeesSource, PurchaseStatus purchaseStatus);

    @Named("calculateTotalAmount")
    default Double calculateTotalAmount(List<PurchaseFeeEntity> purchaseFeesSource) {
        return purchaseFeesSource.stream().mapToDouble(PurchaseFeeEntity::getFeeAmount).sum();
    }



    @Mapping(source = "feeSource.id", target = "feeId")
    @Mapping(source = "feeSource.name", target = "feeName")
    @Mapping(source = "feeSource.amount", target = "feeAmount")
    @Mapping(source = "feeSource.currency", target = "feeCurrency")
    PurchaseFeeEntity mapToPurchaseFee(StudentFeeDto feeSource);


    Purchase mapToPurchaseDto(PurchaseEntity purchase);

    List<Purchase> mapToPurchaseDtoList(List<PurchaseEntity> purchase);

    PurchaseFeeRequest mapToPurchaseFeeDto(PurchaseFeeEntity purchaseFee);
    List<Purchase> mapToPurchaseListDto(List<PurchaseEntity> purchaseList);



    List<PurchaseFeeRequest> mapToPurchaseFeeListDto(List<PurchaseFeeEntity> purchaseFeeList);
}
