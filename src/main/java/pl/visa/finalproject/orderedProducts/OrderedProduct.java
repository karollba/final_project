package pl.visa.finalproject.orderedProducts;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private double orderedQuantity;
    private double recievedQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private boolean checked;
    private boolean matches;

    private LocalDateTime orderDate;

}
