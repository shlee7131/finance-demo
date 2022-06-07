package com.shlee7131.financedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name = "fk_account_subject", columnList="account_subject_id", unique = false)})
public class Ledger extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AccountSubject account_subject;

    @Column(nullable = false)
    private int debit_amount;
    @Column(nullable = false)
    private int credit_amount;

    private String briefs;

    public void setAccount_subject(AccountSubject account_subject) {
        this.account_subject = account_subject;
    }

    public void setDebit_amount(int debit_amount) {
        this.debit_amount = debit_amount;
    }

    public void setCredit_amount(int credit_amount) {
        this.credit_amount = credit_amount;
    }

    public void setBriefs(String briefs) {
        this.briefs = briefs;
    }
}