package com.shlee7131.financedemo.entity;


import com.shlee7131.financedemo.entity.enums.AccountSubjectCategory;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class AccountSubject extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private AccountSubjectCategory accountSubjectCategory;

    @OneToMany(mappedBy = "accountSubject")
    private List<Ledger> ledgers = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLedgers(Ledger ledger) {
        this.ledgers.add(ledger);
    }
}
