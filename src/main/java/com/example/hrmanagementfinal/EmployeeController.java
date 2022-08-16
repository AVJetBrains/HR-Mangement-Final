package com.example.hrmanagementfinal;

import com.example.hrmanagementfinal.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;
}
