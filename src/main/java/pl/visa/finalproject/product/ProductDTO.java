package pl.visa.finalproject.product;

import lombok.Getter;
import lombok.Setter;
import pl.visa.finalproject.orderedProducts.Unit;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {
    private UUID id;
    private String name;
    private boolean availability;
    private double quantity;
//    private LocalDate expirationDate;
    private boolean deleted;
    private LocalDateTime timeDeleted;
    private Unit deafultUnit;
    private double totalQuantity;
    private String barcode;
    private ProductCategory category;
    private Unit defaultUnit;

    public ProductDTO(Product p, double totalQuantity){}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.availability = product.isAvailability();
        this.quantity = product.getQuantity();
//        this.expirationDate = product.getExpirationDate();
        this.deleted = product.isDeleted();
        this.timeDeleted = product.getTimeDeleted();
        this.barcode = product.getBarcode();
        this.deafultUnit = product.getDefaultUnit();
        this.totalQuantity = getTotalQuantity();
    }
}
