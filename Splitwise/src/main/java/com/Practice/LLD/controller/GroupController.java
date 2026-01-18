package com.Practice.LLD.controller;

import com.Practice.LLD.dto.ExpenseDto;
import com.Practice.LLD.dto.GroupCreationDto;
import com.Practice.LLD.entities.Expense;
import com.Practice.LLD.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/group")
@Controller
public class GroupController {

    private final GroupService groupService;
    private final ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<GroupCreationDto> createNewGroup(@RequestBody GroupCreationDto group){
        GroupCreationDto groupCreationDto = groupService.createGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(groupCreationDto);
    }
    @PostMapping("/{id}/expense")
    public ResponseEntity<HttpStatus> addExpense(@PathVariable(name = "id") Long id, @RequestBody ExpenseDto expenseDto){
        return groupService.addGroupExpense(modelMapper.map(expenseDto, Expense.class),id);
    }

}
