package pl.visa.finalproject.delivery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.orderedProducts.OrderedProduct;
import pl.visa.finalproject.orderedProducts.ProductOrder;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDTO {

    private UUID id;
    private Employee acceptingEmployee;
    private Long invoiceId;
    private String deliveryId;
    private LocalDate invoiceDue;
    private LocalDate dateOfAcceptTheDelivery;
    private boolean paid;
    private boolean deliveryIntact;
    private DeliveryCategory category;
    private Long idToShow;
    private Supplier supplier;
    private List<OrderedProduct> orderedProducts = new ArrayList<>();
    private ProductOrder order;
    private boolean deleted;
    private LocalDateTime timeDeleted;


    public DeliveryDTO(Delivery delivery){
        this.id = delivery.getId();
        this.idToShow = delivery.getIdToShow();
        this.deliveryId = delivery.getDeliveryId();
        this.supplier = delivery.getSupplier();
        this.acceptingEmployee = delivery.getAcceptingEmployee();
        this.invoiceDue = delivery.getInvoiceDue();
        this.dateOfAcceptTheDelivery = delivery.getDateOfAcceptTheDelivery();
        this.paid = delivery.isPaid();
        this.deliveryIntact = delivery.isDeliveryIntact();
        this.category = delivery.getCategory();
    }

}
