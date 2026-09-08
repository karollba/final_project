package pl.visa.finalproject.product;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductBatchService {
    private final ProductBatchRepository productBatchRepository;

    public ProductBatchService(ProductBatchRepository productBatchRepository) {
        this.productBatchRepository = productBatchRepository;
    }

    public void add(ProductBatch batch) {
        productBatchRepository.save(batch);
    }

    public List<ProductBatch> findAll() {
        return productBatchRepository.findAll();
    }

    public Optional<ProductBatch> findById(UUID id) {
        return productBatchRepository.findById(id);
    }

    public List<ProductBatch> findByProduct(Product product) {
        return productBatchRepository.findByProduct(product);
    }

    public List<ProductBatch> findFiltered(String category, String expiryFilter) {
        ProductCategory cat = (category != null && !category.isEmpty()) ? ProductCategory.valueOf(category) : null;

        LocalDate expiryLimit = null;
        if ("today".equals(expiryFilter)) {
            expiryLimit = LocalDate.now();
        } else if ("week".equals(expiryFilter)) {
            expiryLimit = LocalDate.now().plusDays(7);
        }

        return productBatchRepository.findFiltered(cat, expiryLimit);
    }

    public double getTotalQuantity(Product product) {
        Double total = productBatchRepository.getTotalQuantity(product, LocalDate.now());
        return total != null ? total : 0;
    }

}
