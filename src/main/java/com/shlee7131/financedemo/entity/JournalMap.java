package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
@Table(indexes = {@Index(name = "fk_transactionCase", columnList = "transactionCase")})
public class JournalMap extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private TransactionCase transactionCase;

    @ManyToOne
    @JoinColumn
    private AccountSubject debitSubject;

    @ManyToOne
    @JoinColumn
    private AccountSubject creditSubject;
}