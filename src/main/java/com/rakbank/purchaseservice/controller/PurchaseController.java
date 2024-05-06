package com.rakbank.purchaseservice.controller;

import com.rakbank.purchaseservice.core.data.ErrorResponse;
import com.rakbank.purchaseservice.core.exception.BusinessException;
import com.rakbank.purchaseservice.data.dto.Purchase;
import com.rakbank.purchaseservice.data.dto.PurchaseCustomAmountRequest;
import com.rakbank.purchaseservice.data.dto.PurchaseRequest;
import com.rakbank.purchaseservice.data.dto.PurchaseStatusUpdateRequest;
import com.rakbank.purchaseservice.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rakbank.purchaseservice.core.constants.Errors.PURCHASE_NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;


    @Operation(
            description = "Create a  Purchase",
            tags = {"Purchase"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    }
    )
    @PostMapping
    public Purchase create(@RequestBody PurchaseRequest request) {
        return purchaseService.create(request);
    }



    @Operation(
            description = "Get Purchase  by studentId",
            tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @GetMapping(path = "/student/{studentId}")
    public List<Purchase> getPurchaseByStudentId(@PathVariable Long studentId) {
        return  purchaseService.findPurchaseByStudentId(studentId);
    }

    @Operation(
            description = "Get Purchase  by id",
            tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @GetMapping(path = "/{id}")
    public Purchase getPurchaseById(@PathVariable String id) {
        return purchaseService.findPurchaseById(id).orElseThrow(() -> new BusinessException(PURCHASE_NOT_FOUND));
    }

    @Operation(
            description = "Update Purchase Custom Amount",
            tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @PutMapping(path = "/custom-amount-update/{purchaseId}")
    public Purchase updateCustomAmount(@PathVariable String purchaseId, @RequestBody PurchaseCustomAmountRequest request) {
        return purchaseService.updateCustomAmount(request.getCustomAmount(),purchaseId).orElseThrow(() -> new BusinessException(PURCHASE_NOT_FOUND));
    }

    @Operation(
            description = "Update Purchase Status",
            tags = {"Purchase"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @PostMapping(path = "/status-update/{purchaseId}")
    public Purchase updateStatus(@PathVariable String purchaseId, @RequestBody PurchaseStatusUpdateRequest request) {
        return purchaseService.updatePurchaseStatus(request.getStatus().toString(),purchaseId).orElseThrow(() -> new BusinessException(PURCHASE_NOT_FOUND));
    }
}
