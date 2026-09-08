package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;
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


    public void add(OrderedProduct orderedProduct) {
        orderedProductRepository.save(orderedProduct);
    }

    public List<OrderedProduct> findAll() {
        return orderedProductRepository.findAll();
    }

    public List<OrderedProduct> findBYDelivery(Delivery delivery) {
        return orderedProductRepository.findByDelivery(delivery);
    }

    public void updateRecievedQuantity(UUID id, double recievedQuantity) {
        OrderedProduct item = orderedProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono dostawy o id: "));

        item.setRecievedQuantity(recievedQuantity);
        item.setChecked(true);
        item.setMatches(item.getOrderedQuantity() == recievedQuantity);
        orderedProductRepository.save(item);
    }

    public void addRecievedQuantity(UUID id, double quantity) {
        OrderedProduct item = orderedProductRepository.findById(id).orElseThrow();

        item.setRecievedQuantity(item.getRecievedQuantity() + quantity);
        item.setMatches(item.getOrderedQuantity() == item.getRecievedQuantity());
        orderedProductRepository.save(item);
    }



}
