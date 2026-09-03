package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
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

    // czy mrozonki czy regular???
    private String deliveryCategory;

    private UUID deliveryManId;

    private LocalDateTime dateOfAcceptTheDelivery;

    private boolean deliveryIntact;

    // tutaj zeby z automatu przekazalo do tabeli tego co jest zalogowany.
    private UUID acceptingEmployeeId;


}
