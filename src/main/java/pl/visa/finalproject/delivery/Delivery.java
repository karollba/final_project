package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.orderedProducts.OrderedProduct;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
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

    // tabela laczona ile i co zamowiono a ile otrzymano (i tru false czy sie zgadzala czy nie)

    // zeby przy wpiswyaniu produktow ktore przyszly (ilosci podkreslalo na czerdwono co sie nie zgadza)
    // a jesli w ogole danego produktu nie ma to opcja do odzanczenia "braw w dosawie" przy danym produkcie

    // lista produktow ktore przywiozl
    // wtedy skanujesz barcode wpisujesz ile masz i ci sprawdza czy zgadza sie z tym co zamowione bylo


    // przy wpisywaniu nowej dostawy po wybraniu/ wpisaniu id zeby samo sie uzupelnila reszta???

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long deliveryManId;
    // tutaj zeby z automatu przekazalo do tabeli tego co jest zalogowany.
    @ManyToOne
    @JoinColumn(name = "accepting_employee_id")
    private Employee acceptingEmployee;

    private UUID acceptingEmployeeId;
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
}
