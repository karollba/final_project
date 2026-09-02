package pl.visa.finalproject.product;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private boolean availability;
    private int quantity;
    private String category;
    private LocalDate expirationDate;
    private boolean deleted;
    private LocalDateTime timeDeleted;
    private String barcode;

    public ProductDTO(){}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.availability = product.isAvailability();
        this.quantity = product.getQuantity();
        this.expirationDate = product.getExpirationDate();
        this.deleted = product.isDeleted();
        this.timeDeleted = product.getTimeDeleted();
        this.barcode = product.getBarcode();
    }
}
