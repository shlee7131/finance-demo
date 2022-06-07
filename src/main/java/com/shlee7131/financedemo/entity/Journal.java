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
    private AccountSubject debitSubject;
    @Enumerated(EnumType.STRING)
    private JournalAccountSign debitSign;
    @Column(nullable = false)
    private int debitAmount;

    @ManyToOne
    private AccountSubject creditSubject;
    @Enumerated(EnumType.STRING)
    private JournalAccountSign creditSign;
    @Column(nullable = false)
    private int creditAmount;

    private String briefs;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setDebitSubject(AccountSubject accountSubject) {
        this.debitSubject = accountSubject;
    }

    public void setDebitSign(JournalAccountSign debitSign) {
        this.debitSign = debitSign;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public void setCreditSubject(AccountSubject creditSubject) {
        this.creditSubject = creditSubject;
    }

    public void setCreditSign(JournalAccountSign creditSign) {
        this.creditSign = creditSign;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public void setBriefs(String briefs) {
        this.briefs = briefs;
    }
}
