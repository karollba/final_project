package pl.visa.finalproject.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRespository  extends JpaRepository<Supplier, UUID> {
}
