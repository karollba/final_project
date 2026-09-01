package pl.visa.finalproject.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping("/all")
//    public List<Employee> allEmployees() {
//        return employeeService.findAll();
//    }

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "list";

    }

    // walidacja pamietaj!
    // hash hasla!

//    @GetMapping("/add/{firstName}/{lastName}/{adminAccess}/{password}")
//    public String add(@PathVariable String firstName,
//                      @PathVariable String lastName,
//                      @PathVariable boolean adminAccess,
//                      @PathVariable String password) {
//
//        Employee employee = new Employee();
//
//        employee.setPassword(password);
//        employee.setLastName(lastName);
//        employee.setFirstName(firstName);
//        employee.setAdminAccess(adminAccess);
//        employeeService.save(employee);
//
//        return "ok";
//    }

    // dodawanie nowego pracownika

    @GetMapping("/add")
    public String addForm() {
        return "add";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        employeeService.add(employee);
       return "redirect:/employee/list";
    }


    // update pracownika



}
