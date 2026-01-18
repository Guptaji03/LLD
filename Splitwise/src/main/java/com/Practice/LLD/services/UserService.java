package com.Practice.LLD.services;

import com.Practice.LLD.dto.UserDto;
import com.Practice.LLD.entities.User;
import com.Practice.LLD.exception.ResourceNotFoundException;
import com.Practice.LLD.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto createNewUser(UserDto userDto) {
            User user = modelMapper.map(userDto, User.class);
            // Save
            User createdUser = userRepository.save(user);
            log.info("User created with id - {}", createdUser.getId());
            return modelMapper.map(createdUser, UserDto.class);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id - "+ id));
    }

    public List<User> getAllUserById(List<Long> users) {
        return userRepository.findAllById(users);
    }
}

