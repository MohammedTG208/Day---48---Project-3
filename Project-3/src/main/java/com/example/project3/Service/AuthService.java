package com.example.project3.Service;

import com.example.project3.API.APIException;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;



    public List<User> getAllUsersForAdmin(Integer adminId) {
        User user = authRepository.findUserById(adminId);
        if(user == null) {
            throw new APIException("admin not found");
        }else {
            return authRepository.findAll();
        }
    }

    public void deleteUserByAdmin(Integer userId, Integer id) {
        User user = authRepository.findUserById(userId);
        if(user == null) {
            throw new APIException("User not found to deleted");
        }else {
            customerRepository.deleteById(id);
            authRepository.deleteById(user.getCustomer().getId());
        }
    }

    public User getUserInfo(Integer userId) {
        User user = authRepository.findUserById(userId);
        if(user == null) {
            throw new APIException("Employee not found in the system");
        }else {
            return user;
        }
    }

    public void updateUser(User user, Integer UserId) {
        User user1 = authRepository.findUserById(UserId);
        if(user1 == null) {
            throw new APIException("User not found to update");
        }else {
            user1.setEmail(user.getEmail());
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            user1.setUsername(user1.getUsername());
            authRepository.save(user1);
        }
    }
}
