package pl.visa.finalproject.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // pamietaj o hashu hasla
    private String password;

    @NotBlank(message = "Pole imię nie może być puste")
    @Min(value = 4, message = "Imię musi mieć minim 4 znaki")
    private String firstName;

    @NotBlank(message = "Pole nazwisko nie może być puste")
    @Min(value = 4, message = "Nazwisko musi mieć min 4 znaki!")
    private String lastName;

    // zastanow sie nad usuwaniem pracownikow (moze odejsc ale jak bylo robione ze nastepni pracownicy "przejmowali stare konta"

    // to do zastanowienia, czy moze usuwac/ mocno ingerowac w baze
    private boolean adminAccess;

    private boolean deleted;

    private LocalDateTime timeDeleted;
}
