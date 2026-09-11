package pl.visa.finalproject.employee;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private boolean adminAccess;
    private boolean deleted;
    private LocalDateTime timeDeleted;
    private Long idToShow;
    private String login;
    public EmployeeDTO() {}

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.idToShow = employee.getIdToShow();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.deleted = employee.isDeleted();
        this.timeDeleted = employee.getTimeDeleted();
        this.login = employee.getLogin();
    }


}
