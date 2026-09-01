package pl.visa.finalproject.employee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;

    // pamietaj o hashu hasla
    private String password;
    private String firstName;
    private String lastName;

    // to do zastanowienia, czy moze usuwac/ mocno ingerowac w baze
    private boolean adminAccess;

    private boolean deleted;

    private LocalDateTime timeDeleted;
    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.deleted = employee.isDeleted();
        this.timeDeleted = employee.getTimeDeleted();
    }


}
