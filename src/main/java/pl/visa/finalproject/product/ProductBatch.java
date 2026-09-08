package pl.visa.finalproject.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Product product;

    private LocalDate expirationDate;

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

        long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if(daysUntilExpiry <= 0) return "danger";
        if(daysUntilExpiry <= 7) return "danger";

        return "";
    }
}
