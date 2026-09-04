package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private UUID acceptingEmployeeId;
    private Long invoiceId;
    private String deliveryId;
    private LocalDate invoiceDue;
    private LocalDate dateOfAcceptTheDelivery;
    private boolean paid;
    private boolean deliveryIntact;
    private String category;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    // czy mrozonki czy regular???
    private String deliveryCategory;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

}
