package pl.visa.finalproject.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findById(UUID id);

    @Query("select MAX(e.idToShow) from Employee e")
    Optional<Long> findMaxIdToShow();

    @Query("select e from Employee e order by e.idToShow asc")
    List<Employee> findAllOrderByIdToShowAsc();
}
