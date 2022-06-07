package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name = "fk_account_subject", columnList="account_subject_id", unique = false)})
public class Ledger extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AccountSubject accountSubject;

    @Column(nullable = false)
    private int debitAmount;
    @Column(nullable = false)
    private int creditAmount;

    private String briefs;

    public void setAccountSubject(AccountSubject account_subject) {
        this.accountSubject = account_subject;
    }

    public void setDebitAmount(int debit_amount) {
        this.debitAmount = debit_amount;
    }

    public void setCreditAmount(int credit_amount) {
        this.creditAmount = credit_amount;
    }

    public void setBriefs(String briefs) {
        this.briefs = briefs;
    }
}