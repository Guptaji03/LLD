package com.Practice.LLD.entities;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="user_details")
public class User {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long phoneNo;

    @Column(nullable = false)
    private String email;

//    @OneToOne
//    @JoinColumn(name = "balance_sheet_id")
//    private BalanceSheet balanceSheet;


}
