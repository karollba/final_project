package pl.visa.finalproject.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.visa.finalproject.employee.Employee;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {


    @Query("select MAX(e.idToShow) from Delivery e")
    Optional<Long> findMaxIdToShow();
}
