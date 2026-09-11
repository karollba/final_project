package pl.visa.finalproject.supplier;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.product.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

     public void add(Supplier supplier) {
         Long maxId = supplierRepository.findMaxIdToShow().orElse(0L);
         supplier.setIdToShow(maxId + 1);
        supplierRepository.save(supplier);
    }


    public List<SupplierDTO> search(String query) {
        if (query == null || query.isEmpty()) {
            return findAll();
        }
        List<Supplier> suppliers = supplierRepository.search(query);
        return suppliers.stream()
                .map(SupplierDTO::new)
                .collect(Collectors.toList());
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll().stream().map(SupplierDTO::new).collect(Collectors.toList());
    }

    public Optional<Supplier> get(UUID id) {
        return supplierRepository.findById(id);
    }

    public Optional<Supplier> findById(UUID product_id) {
        return supplierRepository.findById(product_id);
    }

    public void save(Supplier supplier) {

        if (supplier.getIdToShow() == null) {
            Optional<Long> maxId = supplierRepository.findMaxIdToShow();
            if (maxId.isEmpty())  {
                supplier.setIdToShow(1L);
            } else {
                supplier.setIdToShow(maxId.orElse(null) + 1);
            }
        }
        supplierRepository.save(supplier);
    }

    public boolean exists(UUID id) {
        return supplierRepository.existsById(id);
    }

    public void update(Supplier updatedSupplier) {
        Supplier existing = supplierRepository.findById(updatedSupplier.getId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono dostawcy"));

        if (updatedSupplier.getName() != null && !updatedSupplier.getName().isEmpty()) {
            existing.setName(updatedSupplier.getName());
        }

        if (updatedSupplier.getCity() != null && !updatedSupplier.getCity().isEmpty()) {
            existing.setCity(updatedSupplier.getCity());
        }

        if (updatedSupplier.getNIP() != null) {
            existing.setNIP(updatedSupplier.getNIP());
        }

        if (updatedSupplier.getREGON() != null) {
            existing.setREGON(updatedSupplier.getREGON());
        }

        if (updatedSupplier.getPostalCode() != null && !updatedSupplier.getPostalCode().isEmpty()) {
            existing.setPostalCode(updatedSupplier.getPostalCode());
        }

        if (updatedSupplier.getStreet() != null && !updatedSupplier.getStreet().isEmpty()) {
            existing.setStreet(updatedSupplier.getStreet());
        }
        supplierRepository.save(existing);
    }

    public void delete(UUID id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dostawcy nie znaleziony"));

        supplier.setDeleted(true);
        supplier.setTimeDeleted(LocalDateTime.now());

        supplierRepository.save(supplier);
    }


}
