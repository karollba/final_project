package pl.visa.finalproject.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRespository  extends JpaRepository<Supplier, UUID> {
    @Query("select MAX(e.idToShow) from Supplier e")
    Optional<Long> findMaxIdToShow();
}
