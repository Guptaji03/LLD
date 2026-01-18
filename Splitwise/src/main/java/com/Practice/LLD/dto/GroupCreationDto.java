package com.Practice.LLD.dto;


import com.Practice.LLD.entities.Expense;
import com.Practice.LLD.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreationDto {

    @NotNull
    private String name;

    @NotNull
    private Long ownerId;

    @NotNull
    private String description;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Long> users;

}
