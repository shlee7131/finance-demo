package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class TransactionCase extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "transactionCase", fetch = FetchType.LAZY)
    private List<JournalMap> journalMapIds = new ArrayList<>();
}
