package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.entity.enums.JournalAccountSign;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name="fk_account", columnList = "account_id", unique = false)})
public class Journal extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    private AccountSubject debit_subject;
    @Enumerated(EnumType.STRING)
    private JournalAccountSign debit_sign;
    @Column(nullable = false)
    private int debit_amount;

    @ManyToOne
    private AccountSubject credit_subject;
    @Enumerated(EnumType.STRING)
    private JournalAccountSign credit_sign;
    @Column(nullable = false)
    private int credit_amount;

    private String briefs;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDebit_subject(AccountSubject debit_subject) {
        this.debit_subject = debit_subject;
    }

    public void setDebit_sign(JournalAccountSign debit_sign) {
        this.debit_sign = debit_sign;
    }

    public void setDebit_amount(int debit_amount) {
        this.debit_amount = debit_amount;
    }

    public void setCredit_subject(AccountSubject credit_subject) {
        this.credit_subject = credit_subject;
    }

    public void setCredit_sign(JournalAccountSign credit_sign) {
        this.credit_sign = credit_sign;
    }

    public void setCredit_amount(int credit_amount) {
        this.credit_amount = credit_amount;
    }

    public void setBriefs(String briefs) {
        this.briefs = briefs;
    }
}
