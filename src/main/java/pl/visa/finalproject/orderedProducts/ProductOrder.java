package pl.visa.finalproject.orderedProducts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private boolean deleted;
    private LocalDateTime timeDeleted;
}