package pl.visa.finalproject.supplier;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRespository supplierRespository;

    public SupplierService(SupplierRespository supplierRespository) {
        this.supplierRespository = supplierRespository;
    }

     public void add(Supplier supplier) {
         Long maxId = supplierRespository.findMaxIdToShow().orElse(0L);
         supplier.setIdToShow(maxId + 1);
        supplierRespository.save(supplier);
    }


    public List<Supplier> findAll() {
        return supplierRespository.findAll();
    }

    public Optional<Supplier> get(UUID id) {
        return supplierRespository.findById(id);
    }

    public Optional<Supplier> findById(UUID product_id) {
        return supplierRespository.findById(product_id);
    }

    public void save(Supplier supplier) {
        supplierRespository.save(supplier);
    }

    public boolean exists(UUID id) {
        return supplierRespository.existsById(id);
    }

    public void update(Supplier updatedSupplier) {
        Supplier existing = supplierRespository.findById(updatedSupplier.getId())
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
        supplierRespository.save(existing);
    }


}
