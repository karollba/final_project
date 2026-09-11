package pl.visa.finalproject.orderedProducts;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = true)
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotBlank
    private double orderedQuantity;
    @NotBlank(message = "Podaj ilość otrzymanego produktu!")
    private double recievedQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private boolean checked;
    private boolean matches;

    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    private ProductOrder productOrder;

    @NotBlank(message = "Podaj termin ważności!")
    @Future
    private LocalDate expirationDate;
}
