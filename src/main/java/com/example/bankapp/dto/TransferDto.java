package com.example.bankapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransferDto {

    private Long from;
    private Long to;
    private BigDecimal amount;
}
