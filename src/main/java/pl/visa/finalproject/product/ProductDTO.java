package pl.visa.finalproject.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.orderedProducts.Unit;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

public class ProductDTO {
    private UUID id;
    private String name;
    private boolean availability;
    private double quantity;
//    private LocalDate expirationDate;
    private boolean deleted;
    private LocalDateTime timeDeleted;
    private Unit defaultUnit;
    private double totalQuantity;
    private String barcode;
    private ProductCategory category;
    private Long idToShow;

    public ProductDTO(Product product, double totalQuantity) {
        this.id = product.getId();
        this.idToShow = product.getIdToShow();
        this.name = product.getName();
        this.availability = product.isAvailability();
        this.quantity = product.getQuantity();
        this.deleted = product.isDeleted();
        this.timeDeleted = product.getTimeDeleted();
        this.barcode = product.getBarcode();
        this.defaultUnit = product.getDefaultUnit();
        this.totalQuantity = totalQuantity;
        this.category = product.getCategory();

    }
}
