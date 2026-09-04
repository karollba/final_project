package pl.visa.finalproject.orderedProducts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.product.Product;

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
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double orderedQuantity;
    private double recievedQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private boolean checked;
    private boolean matches;

}
