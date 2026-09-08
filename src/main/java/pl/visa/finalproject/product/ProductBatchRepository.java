package pl.visa.finalproject.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatch, UUID> {

    @Query("select pb from ProductBatch pb " +
            "where (:category is null or pb.product.category = :category) " +
            "and (:today is null or pb.expirationDate <= :today)")
    List<ProductBatch> findFiltered(@Param("category") ProductCategory category,
                               @Param("today") LocalDate expiryLimit);

    // łączna ilośc danego produktu
    @Query("select coalesce(sum(pb.quantity), 0) from ProductBatch pb where pb.product = :product and pb.expirationDate >= :today")
    Double getTotalQuantity(@Param("product") Product product);


}
