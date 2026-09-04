package pl.visa.finalproject.employee;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAllOrderByIdToShowAsc();
    }

    public void add(Employee employee) {
        String hashed = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
        employee.setPassword(hashed);
        Long maxId = employeeRepository.findMaxIdToShow().orElse(0L);
        employee.setIdToShow(maxId + 1);
        employeeRepository.save(employee);
    }

    public Employee findById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // a jak z tym remove? bo bedzie ciezko c


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

}
