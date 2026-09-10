package pl.visa.finalproject.product;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public List<ProductBatch> findByProductFiltered(Product product, String expiryFilter) {
       List<ProductBatch> batches = findByProduct(product);
        LocalDate today = LocalDate.now();

       if ("today".equals(expiryFilter)) {
           batches = batches.stream()
                   .filter(b -> b.getExpirationDate().isEqual(today))
                   .collect(Collectors.toList());
       } else if ("week".equals(expiryFilter)) {
           LocalDate week = LocalDate.now().plusDays(7);
           batches = batches.stream()
                   .filter(b -> !b.getExpirationDate().isBefore(today) && !b.getExpirationDate().isAfter(week))
                   .collect(Collectors.toList());
       }
       return batches;
    }

    public double getTotalQuantity(Product product) {
        Double total = productBatchRepository.getTotalQuantity(product, LocalDate.now());
        return total != null ? total : 0;
    }

    public void delete(UUID id) {
        ProductBatch product = productBatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));

        product.setDeleted(true);
        product.setTimeDeleted(LocalDateTime.now());

        productBatchRepository.save(product);
    }


}
