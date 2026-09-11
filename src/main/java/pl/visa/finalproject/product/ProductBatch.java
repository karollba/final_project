package pl.visa.finalproject.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import pl.visa.finalproject.delivery.Delivery;
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
public class ProductBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    @NotNull(message = "Produkt jest wymagany")
    private Product product;

    @NotNull(message = "Termin ważności jest wymagany")
    @Future(message = "Termin musi być w przyszłości")
    private LocalDate expirationDate;

    @Min(value = 0, message = "Ilość nie może być ujemna!")
    @NotBlank
    private double quantity;
    private LocalDateTime deliveryDate;

    @Enumerated(EnumType.STRING)
    private Unit defaultUnit;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Transient
    public String getExpiryStatus() {
        if (expirationDate == null) return "";

        LocalDate today = LocalDate.now();
        if (expirationDate.isBefore(today)) return "danger";
        if (expirationDate.isEqual(today)) return "danger";
        if (!expirationDate.isAfter(today.plusDays(7))) return "warning";

        return "";
    }

    private boolean deleted;
    private LocalDateTime timeDeleted;

}
