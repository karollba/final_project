package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductBatch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    public OrderedProductService(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
    }


    public Optional<OrderedProduct> findById(UUID id) {
        return orderedProductRepository.findById(id);
    }


    public void save(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    public void add(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    public List<OrderedProduct> findAll() {
        return orderedProductRepository.findAll();
    }

    public List<OrderedProduct> findBYDelivery(Delivery delivery) {
        return orderedProductRepository.findByDelivery(delivery);
    }

    public void assignToDelivery(UUID itemId, Delivery delivery) {
        OrderedProduct item = orderedProductRepository.findById(itemId).orElseThrow();
        item.setDelivery(delivery);
        orderedProductRepository.save(item);
    }


    public List<OrderedProduct> findByProductOrder(ProductOrder order) {
        return orderedProductRepository.findByProductOrder(order);
    }


}
