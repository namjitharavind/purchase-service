package com.rakbank.purchaseservice.repository;



import com.rakbank.purchaseservice.data.entity.PurchaseEntity;
import com.rakbank.purchaseservice.data.enums.PurchaseStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.ZonedDateTime;
import java.util.List;


public interface PurchaseRepository extends JpaRepository<PurchaseEntity, String> {


    List<PurchaseEntity> findByStudentId(Long studentId);

    @Transactional
    @Modifying
    @Query("UPDATE PurchaseEntity p SET p.customAmount = :customAmount where p.id = :purchaseId")
    void updateCustomAmountByPurchaseId(@Param("customAmount") Double customAmount,@Param("purchaseId")  String purchaseId);

    @Transactional
    @Modifying
    @Query("UPDATE PurchaseEntity p SET p.status = :status , p.paidDate = :paidDate where p.id = :purchaseId")
    void updatePurchaseStatus(@Param("status") PurchaseStatus status, @Param("paidDate") ZonedDateTime paidDate, @Param("purchaseId")  String purchaseId);


}