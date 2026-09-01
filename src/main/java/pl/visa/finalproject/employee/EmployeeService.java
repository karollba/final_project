package pl.visa.finalproject.employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    // CRUD

//    public void add(String firstName, String lastName, boolean adminAccess, String password) {
//        Employee employee = new Employee();
//
//        employee.setAdminAccess(adminAccess);
//        employee.setFirstName(firstName);
//        employee.setLastName(lastName);
//        employee.setPassword(password);
//        employeeRepository.save(employee);
//    }

    public void add(Employee employee) {
        employeeRepository.save(employee);
    }


    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // a jak z tym remove? bo bedzie ciezko c

    // jeszcze to przetestuj
    public void update(Long id, String firstName, String lastName, boolean adminAccess,  String password) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        employee.setAdminAccess(adminAccess);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPassword(password);
        employeeRepository.save(employee);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public boolean exists(Long id) {
        return employeeRepository.existsById(id);
    }

}
