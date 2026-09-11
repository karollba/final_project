package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.orderedProducts.OrderedProduct;
import pl.visa.finalproject.orderedProducts.ProductOrder;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "accepting_employee_id")
    private Employee acceptingEmployee;

    private Long invoiceId;
    private String deliveryId;
    private LocalDate invoiceDue;
    private LocalDate dateOfAcceptTheDelivery;
    private boolean paid;
    private boolean deliveryIntact;

    @Enumerated(EnumType.STRING)
    private DeliveryCategory category;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<OrderedProduct> orderedProducts = new ArrayList<>();

    @ManyToOne
    private ProductOrder order;

    private boolean deleted;
    private LocalDateTime timeDeleted;
}
