package pl.visa.finalproject.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.visa.finalproject.employee.Employee;

@ControllerAdvice
public class GlobalModelAttributes {

    // Employee who's currently logged in
    @ModelAttribute("loggedInEmployee")
    public Employee addLoggedInEmployee(@AuthenticationPrincipal Employee employee) {
        return  employee;
    }
}
