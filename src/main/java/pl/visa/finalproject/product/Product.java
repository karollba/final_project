package pl.visa.finalproject.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.visa.finalproject.orderedProducts.Unit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // nazwa pobierana automatycznie z barcode
    private String name;

    private boolean availability;

    @Min(value = 0, message = "Quantity cannot be below 0!")
    private double quantity;

    private boolean deleted;

    private LocalDateTime timeDeleted;

    @Column(unique = true)
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Enumerated(EnumType.STRING)
    private Unit defaultUnit;

//    @Transient
//    public String getExpiryStatus() {
//        if (expirationDate == null) return "";
//
//        long daysUntilExpiry = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
//        if (daysUntilExpiry <= 0) return "danger";
//        if (daysUntilExpiry <= 7) return "warning";
//        return "";
//    }

    // delivery date moze pobierz i wstaw w delivery. probelm bo jak bedziesz dodawac nowa delivery to bedziesz nadpisywac
    // chyba ze utworzysz nowa powaiana tablee i tam bedzie np product id i ostatnie dostawy i kazdy nowy
    // wiersz bedzie notowal kto przyjal dostawe, kiedy i o ktorej godzinie (rozwiaze to tez probelm ze zle sprawdzona dostawa


    // ze jak produkty sie wyprzedaly to jakby cofalo powiadomienia???

    // plus dodawanie produktow/ sortowanie ile sztuk zostalo do konca danego terminu.
    // (ze jak masz 100 opakowan to np 10 jewst do 06.06; 40 jest to 08.08 etc

    // numer dostawy w jakiej przyszedl dany produkt??? zeby mozna bylo latwo namierzyc jak cos sie zepsulo wczesniej czy cso??
    // to powyzej to juz chyba overengineering



}
