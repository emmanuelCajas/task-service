package com.ecajas.api;


import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;
import com.ecajas.dto.UserRequest;
import com.ecajas.dto.UserResponse;
import com.ecajas.service.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UserResponse> createProject(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest),HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> upDateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(id,userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUSerById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
