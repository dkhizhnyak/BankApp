package com.example.bankapp.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransferStatus {
    private String status;
    private Long accountFromId;
    private Long accountToId;
    private BigDecimal remainingBalance;
}
