package pl.visa.finalproject.supplier;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nazwa dostawcy jest wymagana!")
    private String name;

    @NotNull(message = "NIP jest wymagany!")
    @Column(unique = true)
    @Digits(integer = 10, fraction = 0, message = "NIP musi mieć dokładnie 10 cyfr")
    private Long NIP;

    @NotNull(message = "REGON jest wymagany!")
    @Column(unique = true)
    @Pattern(regexp = "\\d{9}|\\d{14}", message = "REGON musi mieć 9 cyfr")
    private Long REGON;

    @NotBlank(message = "Ulica jest wymagana!")
    private String street;

    @NotBlank(message = "Miejscowość jest wymagana!")
    private String city;

    @NotBlank(message = "Kod pocztowy jest wymagany!")
    private String postalCode;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    private boolean deleted;
    private LocalDateTime timeDeleted;

}
