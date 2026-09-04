package pl.visa.finalproject.orderedProducts;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.visa.finalproject.delivery.Delivery;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, UUID> {
    List<OrderedProduct> findByDelivery(Delivery delivery);
    List<OrderedProduct> findByDeliveryAndMatchesFalse(Delivery delivery);
}
