package com.shlee7131.financedemo.entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class AccountSubject extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "account_subject")
    private List<Ledger> ledgers = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLedgers(Ledger ledger) {
        this.ledgers.add(ledger);
    }
}
