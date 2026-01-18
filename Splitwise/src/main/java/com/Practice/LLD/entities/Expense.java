package com.Practice.LLD.entities;


import com.Practice.LLD.entities.enums.SplitType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "expense_details")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @Size(max = 50)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paid_by_id")
    private User paidBy;

    @ElementCollection
    @CollectionTable(
            name = "expense_distribution",
            joinColumns = @JoinColumn(name = "expense_id")
    )
    @MapKeyColumn(name = "user_id")
    @Column(name = "expenseDistribution")
    private Map<Long, BigDecimal> expenseDistribution = new HashMap<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Enumerated(EnumType.STRING)
    private SplitType splitType;

}
