package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {@Index(name="fk_user", columnList = "user_id", unique = false)})
public class Account extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private CurrencyCode currency_code;

    @OneToMany(mappedBy = "account")
    private List<Journal> journals = new ArrayList<Journal>();

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrency_code(CurrencyCode currency_code) {
        this.currency_code = currency_code;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
