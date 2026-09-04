package pl.visa.finalproject.product;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // jeszcze musisz dodac obsluge bledow (ze jak nie znajdzie nie zapisze do bazy etc to co wtedy

    // find by id

    // find by name

    // lisdt all??? chyba tak srednio bo zapcha baze danych (chyba ze tutja paginacje zrobic i to byloby najrozsadniejsze

    // filtrowanie (produtky datą ktore najszybciej sie zepsują)

    // filtorwanie po tym kto przyjal dostawe???

    // usuwanie

    // dodawanie - przy dodawaniu z automatu zrob availability na true bo dodajesz nowy produtk nie

    // update

    // jeszcze oblsuga bledow zeby nie wywalalo ci calego programu!!!!!

    // ale jesli barcodem bys szukala i dodalo to samo nazwe to by byla pewnosc ze bedzie taka sama i nie bedzie duplikatow

    // nad tym sie zastanow bo name moze sie powtarzac a moze nie>????? plus tez jakis paginacja czy cos bo przy 100 tys produktow zajedziesz baze danych
    public void add(Product product) {
        Optional<Product> existing = productRepository.findByName(product.getName());

        if (existing.isPresent()) {
            Product existingProduct = existing.get();
            existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
            existingProduct.setAvailability(true);
            productRepository.save(existingProduct);
        } else {
            product.setAvailability(true);
            productRepository.save(product);
        }
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
//
//        if (updatedProduct.getCategory() != null && !updatedProduct.getCategory().isEmpty()) {
//            existing.setCategory(updatedProduct.getCategory());
//        }

        if (updatedProduct.getExpirationDate() != null) {
            existing.setExpirationDate(updatedProduct.getExpirationDate());
        }
        productRepository.save(existing);

    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public boolean exists(UUID product_id) {
        return productRepository.existsById(product_id);
    }




}
