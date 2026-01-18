package com.Practice.LLD.services;

import com.Practice.LLD.entities.Expense;
import com.Practice.LLD.entities.enums.SplitType;
import com.Practice.LLD.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseValidatorService expenseValidatorService;

    public ResponseEntity<HttpStatus> addExpense(Expense expense) {
         boolean isValid = expenseValidatorService.validate(expense);
         if(!isValid){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
         }else{
             if(expense.getSplitType().equals(SplitType.PERCENTAGE)){
               Map<Long, BigDecimal> membersExpenses =   expenseValidatorService.convertPercentageToAmount(expense.getAmount(),expense.getExpenseDistribution());
               expense.setExpenseDistribution(membersExpenses);
             }
         }
        Expense savedExpense = expenseRepository.save(expense);
        log.info("Added an Expense successfully  with id- {}" , savedExpense.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
