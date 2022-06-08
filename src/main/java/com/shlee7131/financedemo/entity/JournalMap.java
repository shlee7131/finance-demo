package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
@Table(indexes = {@Index(name = "fk_transactionCase", columnList = "transaction_case_id")})
public class JournalMap extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "transaction_case_id")
    private TransactionCase transactionCase;

    @ManyToOne
    @JoinColumn(name="debit_subject")
    private AccountSubject debitSubject;

    @ManyToOne
    @JoinColumn(name="credit_subject")
    private AccountSubject creditSubject;
}