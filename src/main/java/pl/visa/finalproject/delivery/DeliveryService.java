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

    public void add(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    // zmienic dostawe moze tylko admin!!!!
//    public void update(Delivery updatedDelivery) {
//        Delivery existing = deliveryRepository.findById(updatedDelivery.getId())
//                .orElseThrow(() -> new RuntimeException("Nie znaleziono dostawy o numerze " + updatedDelivery.getDeliveryId()));
//
//
//        // ilosc edytowac
//
//        // kategorie (ktos moze sie machnac wybeirajac zla)
//
//
//    }



    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();}

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
