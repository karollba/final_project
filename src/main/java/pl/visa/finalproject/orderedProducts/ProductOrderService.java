package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.supplier.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    public ProductOrderService(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    public ProductOrder createOrder(Supplier supplier) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long maxNumber = productOrderRepository.findMaxOrderNumber().orElse(0L);
        String orderNumber = today + "-" + (maxNumber + 1);

        ProductOrder order = new ProductOrder();
        order.setOrderNumber(orderNumber);
        order.setSupplier(supplier);
        order.setOrderDate(LocalDate.now());

        return productOrderRepository.save(order);
    }

    public List<ProductOrder> search(String query) {
        if (query == null || query.isEmpty()) {
            return findAll();
        }
        return productOrderRepository.search(query);
    }

    public List<ProductOrder> findAll() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> findById(UUID id) {
        return  productOrderRepository.findById(id);
    }

    public void save(ProductOrder order) {
        productOrderRepository.save(order);
    }

    public Optional<ProductOrder> findByDelivery(Delivery delivery) {
        return productOrderRepository.findByDelivery(delivery);
    }


    public void delete(UUID id) {
        ProductOrder order = productOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dostawa nie znaleziona"));

        order.setDeleted(true);
        order.setTimeDeleted(LocalDateTime.now());

        productOrderRepository.save(order);
    }
}
