package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@Table(name="Users")
@EntityListeners(AuditingEntityListener.class)
@ToString
public class User extends Commons {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private int account_id;

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

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
}
