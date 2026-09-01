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

    @GetMapping("/list")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employee/employeeList";

    }

    // walidacja pamietaj!
    // hash hasla!

    // dodawanie nowego pracownika

    @GetMapping("/add")
    public String addForm() {
        return "employee/employeeAdd";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        employeeService.add(employee);
       return "redirect:/employee/employeeList";
    }


    // update pracownika

    // przy update nadpisujesz haslo (co prowadzi do np pustgego pola :/) ponadto brak hash hasla yet

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        model.addAttribute("employee", employeeService.get(id));
        return "redirect:/employee/list";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.update(employee);
        return "redirect:/employee/employeeList";
    }


}
