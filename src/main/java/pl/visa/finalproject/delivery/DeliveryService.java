package pl.visa.finalproject.delivery;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void add(Delivery delivery) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        Long maxNumber = deliveryRepository.findMaxDeliveryNumber().orElse(0L);
        String deliveryId = today + "-" + (maxNumber + 1);
        delivery.setDeliveryId(deliveryId);

        Long maxId = deliveryRepository.findMaxIdToShow().orElse(0L);
        delivery.setIdToShow(maxId + 1);

        deliveryRepository.save(delivery);
    }

    public List<DeliveryDTO> findAll() {
        return deliveryRepository.findAll().stream().map(DeliveryDTO::new).collect(Collectors.toList());
    }

    public void update(Delivery updateDelivery) {
        Delivery existing = deliveryRepository.findById(updateDelivery.getId())
                .orElseThrow(() -> new RuntimeException("Dostawcy nie znaleziono"));

        if (updateDelivery.getInvoiceDue() != null) {
            existing.setInvoiceDue(updateDelivery.getInvoiceDue());
        }

        existing.setPaid(updateDelivery.isPaid());
        deliveryRepository.save(existing);
    }

    public List<DeliveryDTO> search(String query) {
        if (query == null || query.isEmpty()) {
            return findAll();
        }
        List<Delivery> deliveries = deliveryRepository.search(query);
        return deliveries.stream().map(DeliveryDTO::new).collect(Collectors.toList());
    }


    public Optional<Delivery> get(UUID id) {
        return deliveryRepository.findById(id);
    }

    public Optional<Delivery> findById(UUID id) {
        return deliveryRepository.findById(id);
    }

    public void save(Delivery delivery) {
        if (delivery.getIdToShow() == null) {
            Optional<Long> maxId = deliveryRepository.findMaxIdToShow();

            if (maxId.isEmpty()) {
                delivery.setIdToShow(1L);
            } else {
                delivery.setIdToShow(maxId.orElse(null) + 1);
            }
        }
        deliveryRepository.save(delivery);
    }

    public boolean exists(UUID id) {
        return deliveryRepository.existsById(id);
    }


    public void delete(UUID id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dostawa nie znaleziona"));

        delivery.setDeleted(true);
        delivery.setTimeDeleted(LocalDateTime.now());

        deliveryRepository.save(delivery);
    }
}
