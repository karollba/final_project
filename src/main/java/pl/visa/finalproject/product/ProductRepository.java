package pl.visa.finalproject.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByName(String name);

    Optional<Product> findByBarcode(String barcode);

//    Optional<Product> findById(UUID id);
//
//    boolean existsById(UUID id);
}
