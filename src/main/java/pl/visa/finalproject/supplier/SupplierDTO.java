package pl.visa.finalproject.supplier;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class SupplierDTO {
    private UUID id;
    private String name;
    private Long NIP;
    private Long REGON;
    private String street;
    private String city;
    private String postalCode;
    private Long idToShow;
    private boolean deleted;
    private LocalDateTime timeDeleted;

    public SupplierDTO (Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.NIP = supplier.getNIP();
        this.REGON = supplier.getREGON();
        this.street = supplier.getStreet();
        this.city = supplier.getCity();
        this.postalCode = supplier.getPostalCode();
        this.idToShow = supplier.getIdToShow();

    }
}
