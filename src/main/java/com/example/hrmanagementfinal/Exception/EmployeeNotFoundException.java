package com.example.hrmanagementfinal.Exception;

public class EmployeeNotFoundException extends RuntimeException{

    public String email;


    public EmployeeNotFoundException(String email){
        this.email = email;
    }

    @Override
    public String getMessage(){
        return "Employee Not found with the email "+ email;
    }
}
