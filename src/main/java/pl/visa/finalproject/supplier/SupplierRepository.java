package pl.visa.finalproject.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    @Query("select MAX(e.idToShow) from Supplier e")
    Optional<Long> findMaxIdToShow();


    // nip i regon zeby suzkalo

    @Query("select s from Supplier s where s.deleted = false and " +
            "lower(s.name) like lower(concat('%', :query, '%')) or " +
            "cast(s.idToShow as string ) like %:query% or " +
            "cast(s.NIP as string ) like %:query% or " +
            "cast(s.REGON as string ) like %:query% or " +
            "lower(s.city) like lower(concat('%', :query, '%')) or " +
            "lower(s.street) like lower(concat('%', :query, '%')) ")
    List<Supplier> search(@Param("query") String query);

    @Override
    @Query("select s from Supplier s where s.deleted = false ")
    List<Supplier> findAll();
}
