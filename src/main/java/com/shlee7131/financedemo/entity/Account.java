package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.entity.enums.CurrencyCode;
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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private CurrencyCode currencyCode;

    @OneToMany(mappedBy = "account")
    private List<Journal> journals = new ArrayList<Journal>();

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrencyCode(CurrencyCode currency_code) {
        this.currencyCode = currency_code;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
