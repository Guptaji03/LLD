package com.Practice.LLD.controller;

import com.Practice.LLD.dto.ExpenseDto;
import com.Practice.LLD.entities.Expense;
import com.Practice.LLD.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
@Controller
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<HttpStatus> addExpense(@RequestBody ExpenseDto expenseDto){
        return  expenseService.addExpense(modelMapper.map(expenseDto, Expense.class));
    }

}