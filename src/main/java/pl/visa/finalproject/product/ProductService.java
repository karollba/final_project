package pl.visa.finalproject.product;

import org.springframework.stereotype.Service;
import pl.visa.finalproject.employee.Employee;

import java.util.List;
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

    public void add(Product product) {
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }



    public Product findById(UUID product_id) {
        return productRepository.findById(product_id);
    }


    // uwazaj bo to zmieni wszystkie wiersze danego produktu (nadpisze ci zmiany, jak nei wszystkie beda wypelnione)
    public void update(Product product) {
        productRepository.save(product);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public boolean exists(UUID product_id) {
        return productRepository.existsById(product_id);
    }


}
