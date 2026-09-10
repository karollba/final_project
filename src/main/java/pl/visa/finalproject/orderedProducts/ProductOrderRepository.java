package pl.visa.finalproject.orderedProducts;

import org.eclipse.tags.shaded.org.apache.bcel.verifier.statics.LONG_Upper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, UUID> {

    @Query("select max(cast(substring(o.orderNumber, locate('-', o.orderNumber) + 1, 10) as long)) from ProductOrder o")
    Optional<Long> findMaxOrderNumber();

    Optional<ProductOrder> findByDelivery(Delivery delivery);

}
