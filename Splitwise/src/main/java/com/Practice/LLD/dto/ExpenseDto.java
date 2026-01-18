package com.Practice.LLD.dto;

import com.Practice.LLD.entities.enums.SplitType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String title;

    @NotNull
    private Long paidById;

    @NotNull
    private Map<Long,BigDecimal> expenseDistribution;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private SplitType splitType;
}
