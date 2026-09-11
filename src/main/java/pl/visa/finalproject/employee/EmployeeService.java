package pl.visa.finalproject.employee;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAllOrderByIdToShowAsc().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    public void add(Employee employee) {
        String hashed = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        employee.setPassword(hashed);
        Long maxId = employeeRepository.findMaxIdToShow().orElse(0L);
        employee.setIdToShow(maxId + 1);
        employeeRepository.save(employee);
    }

    public List<EmployeeDTO> search(String query) {
        if (query == null || query.isEmpty()) {
            return findAll();
        }
        List<Employee> employees = employeeRepository.search(query);
        return employees.stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    public Employee findById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }


    public void update(Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(updatedEmployee.getId())
                .orElseThrow(() -> new RuntimeException("Pracownik nie znaleziony"));

        if (updatedEmployee.getFirstName() != null && !updatedEmployee.getFirstName().isEmpty()) {
            existing.setFirstName(updatedEmployee.getFirstName());
        }

        if (updatedEmployee.getLastName() != null && !updatedEmployee.getLastName().isEmpty()) {
            existing.setLastName(updatedEmployee.getLastName());
        }

        if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isEmpty()) {
            String hashed = BCrypt.hashpw(updatedEmployee.getPassword(), BCrypt.gensalt());
            existing.setPassword(hashed);
        }

        existing.setAdminAccess(updatedEmployee.isAdminAccess());

        employeeRepository.save(existing);
    }

    public Employee get(UUID id) {
        return employeeRepository.getById(id);
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public boolean exists(UUID id) {
        return employeeRepository.existsById(id);
    }

    public void delete(UUID id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pracownik nie znaleziony"));

        employee.setDeleted(true);
        employee.setTimeDeleted(LocalDateTime.now());

        employeeRepository.save(employee);
    }

}
