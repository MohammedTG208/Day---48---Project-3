package com.example.project3.Controller;

import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/emp")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get-all-for-admin")
    public ResponseEntity getAllForAdmin(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }
    @PostMapping("/register")
    public ResponseEntity registerEmp(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body("Employee registered successfully");
    }
    @PutMapping("/update")
    public ResponseEntity updateEmp(@AuthenticationPrincipal User user ,@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO,user.getId());
        return ResponseEntity.status(200).body("Employee updated successfully");
    }
    @DeleteMapping("/delete/{empID}")
    public ResponseEntity deleteEmp(@AuthenticationPrincipal User user, @PathVariable Integer empID) {
        employeeService.deleteEmployeeByAdmin(user.getId(),empID);
        return ResponseEntity.status(200).body("Employee deleted successfully");
    }
}
