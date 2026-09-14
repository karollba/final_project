package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Numer faktury jest wymagany")
    private Long invoiceId;

    @NotNull(message = "Numer dostawy jest wymagany!")
    private String deliveryId;

    @Future(message = "Data płatności musi być w przyszłości!")
    @NotNull(message = "Data płatności jest wymagana!")
    private LocalDate invoiceDue;


    @NotNull(message = "Data przyjęcia dostawy jest wymagana")
    private LocalDate dateOfAcceptTheDelivery;

    private boolean paid;
    private boolean deliveryIntact;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Kategoria jest wymagana!")
    private DeliveryCategory category;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @NotNull(message = "Dostawca jest wymagany!")
    private Supplier supplier;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<OrderedProduct> orderedProducts = new ArrayList<>();

    @ManyToOne
    private ProductOrder order;

    private boolean deleted;
    private LocalDateTime timeDeleted;
}
