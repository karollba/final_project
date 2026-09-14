package pl.visa.finalproject.supplier;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Digits(integer = 10, fraction = 0, message = "NIP musi mieć dokładnie 10 cyfr")
    @Column(unique = true)
    private Long NIP;

    @NotNull(message = "REGON jest wymagany!")
    @Digits(integer = 9, fraction = 0, message = "REGON musi mieć 9 cyfr")
    @Column(unique = true)
    private Long REGON;


    private String street;
    private String city;
    private String postalCode;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    private boolean deleted;
    private LocalDateTime timeDeleted;

}
