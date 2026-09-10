package pl.visa.finalproject.product;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductBatchService productBatchService;

    public ProductService(ProductRepository productRepository, ProductBatchService productBatchService) {
        this.productRepository = productRepository;
        this.productBatchService = productBatchService;
    }

    public void add(Product product) {
        Optional<Product> existing = productRepository.findByName(product.getName());

        if (existing.isPresent()) {
            Product existingProduct = existing.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            existingProduct.setAvailability(true);
            productRepository.save(existingProduct);
        } else {
            Long maxId = productRepository.findMaxIdToShow().orElse(0L);
            product.setIdToShow(maxId + 1);
            product.setAvailability(true);
            productRepository.save(product);
        }
    }

    public double getTotalQuantity(Product product) {
        return productBatchService.getTotalQuantity(product);
    }

    public List<Product> findFiltered(String category) {
        ProductCategory cat = (category != null && !category.isEmpty())
                ? ProductCategory.valueOf(category) : null;

        return productRepository.findFiltered(cat);
    }

    public List<ProductDTO> findAllWithTotalQuantity(String category) {
        List<Product> products = findFiltered(category);

        return products.stream()
                .map(p -> new ProductDTO(p, productBatchService.getTotalQuantity(p)))
                .collect(Collectors.toList());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> get(UUID id) {
            return productRepository.findById(id);
    }

    public Optional<Product> findById(UUID product_id) {
        return productRepository.findById(product_id);
    }

    public Optional<Product> findByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    public List<ProductDTO> search(String query) {
        List<Product> products;
        if (query == null || query.isEmpty()) {
            products = findAll();
        } else {
            products = productRepository.search(query);
        }

        return products.stream()
                .map(p -> new ProductDTO(p, productBatchService.getTotalQuantity(p)))
                .collect(Collectors.toList());
    }

    // uwazaj bo to zmieni wszystkie wiersze danego produktu (nadpisze ci zmiany, jak nei wszystkie beda wypelnione)
    public void update(Product updatedProduct) {
        Product existing = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));

        if (updatedProduct.getName() != null && !updatedProduct.getName().isEmpty()) {
            existing.setName(updatedProduct.getName());
        }

        if (updatedProduct.getQuantity() > 0) {
            existing.setQuantity(updatedProduct.getQuantity());
        }
        productRepository.save(existing);

    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public boolean exists(UUID product_id) {
        return productRepository.existsById(product_id);
    }

    public void delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produkt nie znaleziony"));

        product.setDeleted(true);
        product.setTimeDeleted(LocalDateTime.now());

        productRepository.save(product);
    }

}
