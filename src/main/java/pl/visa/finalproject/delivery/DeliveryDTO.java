package pl.visa.finalproject.delivery;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class DeliveryDTO {
    private UUID id;
    private String deliveryCategory;
    private Long deliveryManId;
    private LocalDate dateOfAcceptTheDelivery;
    private boolean deliveryIntact;
    private UUID acceptingEmployeeId;

    public DeliveryDTO() {}

    public DeliveryDTO(Delivery delivery){
        this.acceptingEmployeeId = delivery.getAcceptingEmployeeId();
        this.dateOfAcceptTheDelivery = delivery.getDateOfAcceptTheDelivery();
        this.deliveryManId = delivery.getDeliveryManId();
        this.deliveryIntact = delivery.isDeliveryIntact();
    }

}
