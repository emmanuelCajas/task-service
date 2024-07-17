package com.ecajas.service.ServiceImpl;

import com.ecajas.dto.UserRequest;
import com.ecajas.dto.UserResponse;
import com.ecajas.mapper.UserMapper;
import com.ecajas.model.User;
import com.ecajas.repository.UserRepository;
import com.ecajas.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        userRepository.save(user);
        UserResponse userResponse = userMapper.userToUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("User not found"));
        updateUserFromRequest(user, userRequest);
        user = userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream().map(userMapper::userToUserResponse).toList();
         return userResponses;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));;
        UserResponse userResponse=userMapper.userToUserResponse(user);
        return userResponse;
    }

    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private void updateUserFromRequest(User user, UserRequest request) {
        // Actualiza los campos del usuario
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        if (user.getUserProfile() != null) {
            user.getUserProfile().setFirstName(request.getFirstName());
            user.getUserProfile().setLastName(request.getLastName());
            user.getUserProfile().setDni(request.getDni());
        }
    }
}
