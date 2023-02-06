package com.example.bankapp.mapper;

import com.example.bankapp.entity.Account;
import com.example.bankapp.response.TransferStatus;

public class TransferStatusMapper {

    public static TransferStatus mapToResponse(Account from, Account to) {
        return TransferStatus.builder()
                .accountFromId(from.getId())
                .accountToId(to.getId())
                .status("SUCCESS")
                .remainingBalance(from.getBalance())
                .build();
    }
}
