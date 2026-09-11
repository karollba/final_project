package pl.visa.finalproject.employee;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/search")
    public String searchEmployee(@RequestParam String query, Model model) {
        model.addAttribute("employees", employeeService.search(query));
        model.addAttribute("query", query);
        return "employee/employeeList";
    }



    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employeeAdd";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        employeeService.add(employee);
       return "redirect:/employee/list";
    }


    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        model.addAttribute("employee", employeeService.get(id));
        return "employee/employeeEdit";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.update(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        employeeService.delete(id);
        return "redirect:/employee/list";
    }
}
