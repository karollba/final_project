package pl.visa.finalproject.product;

import jakarta.annotation.security.PermitAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    Optional<Product> findByBarcode(String barcode);

    // produkty z bliskim terminem przydatności do spożycia
//    @Query("select pb from ProductBatch pb where pb.expirationDate between :today and :weeklater")
//    List<ProductBatch> findExpiringBatches(@Param("today") LocalDate today, @Param("weeklater") LocalDate weeklater);




//    Optional<Product> findById(UUID id);
//
//    boolean existsById(UUID id);
}
