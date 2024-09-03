package com.example.project3.Service;

import com.example.project3.API.APIException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void registerEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setRole("EMPLOYEE");
        employeeDTO.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        User user = new User(null, employeeDTO.getUsername(), employeeDTO.getPassword(), employeeDTO.getName(), employeeDTO.getEmail(), employeeDTO.getRole(), null,employee);
        employee.setUser(user);
        authRepository.save(user);
        employeeRepository.save(employee);

    }

    public void updateEmployee(EmployeeDTO employeeDTO,Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        User user = employee.getUser();
        if (employee == null||user == null) {
            throw new APIException("Employee not found");
        }else {
            employee.setSalary(employeeDTO.getSalary());
            employee.setPosition(employeeDTO.getPosition());
            user.setEmployee(employee);
            user.setUsername(employeeDTO.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
            user.setName(employeeDTO.getName());
            user.setEmail(employeeDTO.getEmail());
            authRepository.save(user);
            employeeRepository.save(employee);
        }
    }

    public void deleteEmployeeByAdmin(Integer adminId,Integer employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        User user=authRepository.findUserById(adminId);
        if (employee == null) {
            throw new APIException("Employee not found");
        }else {
            if (user.getRole().equalsIgnoreCase("ADMIN")){
            employeeRepository.deleteById(employeeId);
            }else {
                throw new APIException("Admin only can delete this account");
            }
        }
    }
}
