package pl.visa.finalproject.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> allEmployees() {
        return employeeService.findAll();
    }

    // walidacja pamietaj!
    // hash hasla!
    @GetMapping("/add/{firstName}/{lastName}/{adminAccess}/{password}")
    public String add(@PathVariable String firstName,
                      @PathVariable String lastName,
                      @PathVariable boolean adminAccess,
                      @PathVariable String password) {

        Employee employee = new Employee();

        employee.setPassword(password);
        employee.setLastName(lastName);
        employee.setFirstName(firstName);
        employee.setAdminAccess(adminAccess);
        employeeService.save(employee);

        return "ok";

    }
}
