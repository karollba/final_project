package pl.visa.finalproject.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByUUID(UUID product_id);

    boolean existsByUUID(UUID product_id);
}
