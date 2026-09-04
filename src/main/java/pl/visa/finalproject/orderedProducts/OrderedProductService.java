package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.delivery.Delivery;

import java.util.List;
import java.util.UUID;

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    public OrderedProductService(OrderedProductRepository orderedProductRepository) {
        this.orderedProductRepository = orderedProductRepository;
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


}
