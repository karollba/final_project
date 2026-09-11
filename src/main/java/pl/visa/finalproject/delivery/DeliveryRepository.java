package pl.visa.finalproject.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {


    @Query("select MAX(e.idToShow) from Delivery e ")
    Optional<Long> findMaxIdToShow();

    @Query("select max(cast(substring( d.deliveryId, locate('-', d.deliveryId) + 1, 10) as long)) from Delivery d")
    Optional<Long> findMaxDeliveryNumber();

    @Query("select d from Delivery d where d.deleted = false and (" +
            "cast(d.deliveryId as string ) like %:query% or " +
            "lower(d.supplier.name) like lower(concat('%', :query, '%')) or " +
            "lower(cast(d.category as string)) like lower(concat('%', :query, '%')) or " +
            "lower(cast(d.paid as string )) like lower(concat('%', :query, '%')) or " +
            "cast(d.dateOfAcceptTheDelivery as string ) like %:query%)" )
    List<Delivery> search(@Param("query") String query);

    @Override
    @Query("select d from Delivery d where d.deleted = false ")
    List<Delivery> findAll();

}
