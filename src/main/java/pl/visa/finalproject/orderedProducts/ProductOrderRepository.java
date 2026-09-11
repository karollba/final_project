package pl.visa.finalproject.orderedProducts;

import org.eclipse.tags.shaded.org.apache.bcel.verifier.statics.LONG_Upper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, UUID> {

    @Query("select max(cast(substring(o.orderNumber, locate('-', o.orderNumber) + 1, 10) as long)) from ProductOrder o")
    Optional<Long> findMaxOrderNumber();

    Optional<ProductOrder> findByDelivery(Delivery delivery);

    @Query("select p from ProductOrder p where p.deleted = false and (" +
            "p.orderNumber Like %:query% or " +
            "lower(p.supplier.name) like lower(concat('%', :query, '%')))")
    List<ProductOrder> search(@Param("query") String query);

    @Override
    @Query("select p from ProductOrder p where p.deleted = false ")
    List<ProductOrder> findAll();


}
