package com.ecajas.service;

import com.ecajas.dto.ProjectRequest;
import com.ecajas.dto.ProjectResponse;
import com.ecajas.dto.UserRequest;
import com.ecajas.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest  userRequest);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
    //List<UserResponse> findProjectsByNameContaining(String name);

}
