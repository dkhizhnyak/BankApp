package com.example.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @NotNull
    @JsonIgnore
    private Account account;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column
    private BigDecimal amount;

    @Column
    private String operation;

    public Transaction(Account account, BigDecimal amount, String operation) {
        this.account = account;
        this.date = Date.from(Instant.now());
        this.amount = amount;
        this.operation = operation;
    }

    public Transaction() {}
}
