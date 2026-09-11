package pl.visa.finalproject.employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println("szukam uzytkownika " + login+ "=======");
        return  employeeRepository.findByLogin(login)
                .orElseThrow(() -> {
                    System.out.println("nie znaleziono: " + login + "=====");
                    return new UsernameNotFoundException("Nie znaleziono użytkownika o nazwie " + login);
                });
    }
}
