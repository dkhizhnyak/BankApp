package com.example.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_Id")
    @NotNull
    @JsonIgnore
    private Client client;

    @Column
    private BigDecimal balance;

    public Account(Client client) {
        this.client = client;
        this.balance = BigDecimal.ZERO;
    }

    public Account(Client client, BigDecimal amount) {
        this.client = client;
        this.balance = amount;
    }

    public Account() {}
}
