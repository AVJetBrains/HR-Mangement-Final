package com.example.hrmanagementfinal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/Hello")
    public String sayHello(){
        return "Hello from spring boot in maven";
    }
}
