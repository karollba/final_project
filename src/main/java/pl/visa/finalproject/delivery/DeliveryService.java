package pl.visa.finalproject.delivery;

import org.springframework.stereotype.Service;

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
        Long maxId = deliveryRepository.findMaxIdToShow().orElse(0L);
        delivery.setIdToShow(maxId + 1);
        deliveryRepository.save(delivery);
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public void update(Delivery updateDelivery) {
        Delivery existing = deliveryRepository.findById(updateDelivery.getId())
                .orElseThrow(() -> new RuntimeException("Dostawcy nie znaleziono"));

        // zmiana byla na nazwach bo relacje tworzymy
//        if (updateDelivery.getSupplierName() != null && !updateDelivery.getSupplierName().isEmpty()) {
//            existing.setSupplierName(updateDelivery.getSupplierName());
//        }

//        if (updateDelivery.getCategory() != null && !updateDelivery.getCategory().isEmpty()) {
//            existing.setCategory(updateDelivery.getCategory());
//        }

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
}
