package com.example.moviebookingticket.controller;

import com.example.moviebookingticket.dto.UserDto;
import com.example.moviebookingticket.entity.UserEntity;
import com.example.moviebookingticket.repository.UserRepository;
import com.example.moviebookingticket.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.moviebookingticket.exception.UserNotFoundException;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all users")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(@RequestParam(defaultValue = "0")   Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(userService.getAllUsers(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new user")
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @ApiOperation(value = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @ApiOperation(value = "Update user")
    @PutMapping ("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
}
