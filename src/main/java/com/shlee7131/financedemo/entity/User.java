package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name="Users")
@EntityListeners(AuditingEntityListener.class)
public class User extends Commons {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Account> accounts = new ArrayList<>();

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public boolean matchPassword(String password) {
        if (this.password.equals(password)) return true;
        return false;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }
}
