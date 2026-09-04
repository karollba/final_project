package pl.visa.finalproject.delivery;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService {

    // szukaj po:
        // data z dnia
        // id osoby ktora przywiozla
        // id osoby ktora odebrala
        // produktach (wybierasz jaki produkt i pokazuje ci dni w ktorych zostal dostarczony

    private final DeliveryRepository deliveryRepository;

    // zmienic dostawe moze tylko admin!!!!

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void add(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public void update(Delivery updateDelivery) {
        Delivery existing = deliveryRepository.findById(updateDelivery.getId())
                .orElseThrow(() -> new RuntimeException("Dostawcy nie znaleziono"));

        if (updateDelivery.getSupplierName() != null && !updateDelivery.getSupplierName().isEmpty()) {
            existing.setSupplierName(updateDelivery.getSupplierName());
        }

        if (updateDelivery.getCategory() != null && !updateDelivery.getCategory().isEmpty()) {
            existing.setCategory(updateDelivery.getCategory());
        }

        if (updateDelivery.getInvoiceDue() != null) {
            existing.setInvoiceDue(updateDelivery.getInvoiceDue());
        }

        existing.setPaid(updateDelivery.isPaid());
        deliveryRepository.save(existing);
    }

    public Optional<Delivery> get(UUID id) {
        return deliveryRepository.findById(id);
    }

    public Optional<Delivery> findById(UUID id) {
        return deliveryRepository.findById(id);
    }

    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public boolean exists(UUID id) {
        return deliveryRepository.existsById(id);
    }
}
