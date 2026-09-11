package pl.visa.finalproject.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findById(UUID id);

    @Query("select MAX(e.idToShow) from Employee e")
    Optional<Long> findMaxIdToShow();

    @Query("select e from Employee e where e.deleted = false " +
            "order by e.idToShow asc")
    List<Employee> findAllOrderByIdToShowAsc();

    @Query("select e from Employee e where e.deleted = false and (" +
            "cast(e.idToShow as string) like %:query% or " +
            "lower(e.firstName) like lower(concat('%', :query, '%')) or " +
            "lower(e.lastName) like lower(concat('%', :query, '%'))) ")
    List<Employee> search(@Param("query") String query);


    Optional<Employee> findByLogin(String login);
}
