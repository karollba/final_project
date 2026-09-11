package pl.visa.finalproject.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.orderedProducts.Unit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long idToShow;

    @NotBlank(message = "Nazwa jest wymagana")
    private String name;

    private boolean availability;

    @Min(value = 0, message = "Ilość nie może być ujemna!")
    @NotBlank
    private double quantity;

    private boolean deleted;

    private LocalDateTime timeDeleted;

    @Column(unique = true)
    @NotBlank(message = "Kod kreskowy jest wymagany")
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private Unit defaultUnit;


}
