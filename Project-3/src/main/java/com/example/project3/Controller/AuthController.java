package com.example.project3.Controller;


import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @GetMapping("/get-all-users-for-admin")
    public ResponseEntity GetAllUsersForAdmin(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(authService.getAllUsersForAdmin(user.getId()));
    }

    @GetMapping("/get-user-info")
    public ResponseEntity GetUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(authService.getUserInfo(user.getId()));
    }
    @DeleteMapping("/delete-by-admin/{userId}")
    public ResponseEntity deleteByAdmin(@AuthenticationPrincipal User user, @PathVariable Integer userId) {
        authService.deleteUserByAdmin(user.getId(),userId);
        return ResponseEntity.status(200).body("User deleted successfully");
    }
    @PutMapping("/update-user")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user, @Valid @RequestBody User user1) {
        authService.updateUser(user1,user.getId());
        return ResponseEntity.status(200).body("updated user successfully");
    }

}
