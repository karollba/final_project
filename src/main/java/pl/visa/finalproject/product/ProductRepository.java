package pl.visa.finalproject.product;

import jakarta.annotation.security.PermitAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.visa.finalproject.employee.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    Optional<Product> findByBarcode(String barcode);

    @Query("select p from Product p where (:category is null or p.category = :category)")
    List<Product> findFiltered(@Param("category") ProductCategory category);

    @Query("select MAX(p.idToShow) from Product p")
    Optional<Long> findMaxIdToShow();

    @Query("select p from Product p where " +
            "cast(p.idToShow as string) like %:query% or " +
            "lower(p.name) like lower(concat('%', :query, '%')) or " +
            "lower(p.category) like lower(concat('%', :query, '%')) ")
    List<Product> search(@Param("query") String query);


    // produkty z bliskim terminem przydatności do spożycia
//    @Query("select pb from ProductBatch pb where pb.expirationDate between :today and :weeklater")
//    List<ProductBatch> findExpiringBatches(@Param("today") LocalDate today, @Param("weeklater") LocalDate weeklater);




//    Optional<Product> findById(UUID id);
//
//    boolean existsById(UUID id);
}
