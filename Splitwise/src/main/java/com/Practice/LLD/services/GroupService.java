package com.Practice.LLD.services;


import com.Practice.LLD.dto.GroupCreationDto;
import com.Practice.LLD.entities.Expense;
import com.Practice.LLD.entities.Group;
import com.Practice.LLD.entities.User;

import com.Practice.LLD.exception.ResourceNotFoundException;
import com.Practice.LLD.repositories.GroupRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@RequiredArgsConstructor
@Service
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Transactional
    public ResponseEntity<HttpStatus> addGroupExpense(Expense expense, Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Group not found with id - " + id));
        expense.setGroup(group);
        return expenseService.addExpense(expense);
    }

    @Transactional
    public GroupCreationDto createGroup(GroupCreationDto groupCreationDto) {
        // Get owner user entity directly from repository
        User owner = userService.getUserById(groupCreationDto.getOwnerId());

        // Get member users
        List<User> members = userService.getAllUserById(groupCreationDto.getUsers());

        // Create group
        Group group = new Group();
        group.setName(groupCreationDto.getName());
        group.setDescription(groupCreationDto.getDescription());
        group.setOwner(owner);
        group.setUsers(members);

        Group savedGroup = groupRepository.save(group);

        // Map back to DTO
        GroupCreationDto result = new GroupCreationDto();
        result.setName(savedGroup.getName());
        result.setDescription(savedGroup.getDescription());
        result.setOwnerId(savedGroup.getOwner().getId());
        result.setUsers(savedGroup.getUsers().stream()
                .map(User::getId)
                .toList());

        return result;
    }

}
