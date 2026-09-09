package pl.visa.finalproject.orderedProducts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    private Long orderNumber;
    private LocalDate orderDate;

    @OneToMany(mappedBy = "productOrder")
    private List<OrderedProduct> orderedProducts;
}

//1. Stworzyć encję ProductOrder
//
//2. Dodać @ManyToOne ProductOrder w OrderedProduct
//
//3. Dodać orderNumber
//
// 4. Lista zamówień (orderList.jsp)
//
//    5. Szczegóły zamówienia (orderShow.jsp)
//
//6. Dodawanie produktów do konkretnego zamówienia
//
//7. Przepiąć obecny quantity check na produkty należące do danego zamówienia