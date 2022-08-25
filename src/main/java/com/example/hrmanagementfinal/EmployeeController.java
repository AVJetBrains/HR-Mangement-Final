package com.example.hrmanagementfinal;

import com.example.hrmanagementfinal.security.Roles;
import com.example.hrmanagementfinal.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;


    @Secured({Roles.User})
    @GetMapping("/employees")
    private void getEmployeesName() {
        employeeServices.getEmployees();
    }

    @Secured({Roles.User})
    @GetMapping("/employee?employeeName=?")
    private void getEmployeeDetails(){
        employeeServices.getEmployeeByName("");
    }

}
