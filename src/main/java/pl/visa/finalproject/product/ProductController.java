package pl.visa.finalproject.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/productList";
    }

    // poszukujac filtruj napierw przez kategorie potem szukaj uuid bo tak to zajedziesz baze danych


    // najpierw sprawdz czy produkt juz nie istnieje jesli istnieje to dodaj do istniejacego rekordu juz a jak nie istnieje to utworz nowy rekord
    @GetMapping("/add")
    public String addForm() {
        return "product/productAdd";
    }

    @PostMapping("/add")
    public String add(Product product) {
        // moze zamiast tego tutaj zrobic taki ladny update??? bo to juz troche sie robi paghetticode i nie pwoinno to tez byc w controllerze :)
        if (productService.exists(product.getId())) {
            Optional<Product> existingProduct = productService.findById(product.getId());
            existingProduct.get().setQuantity(existingProduct.get().getQuantity() + product.getQuantity());
            productService.add(existingProduct.orElse(null));
        } else {
            productService.add(product);
        }

        return "redirect:/product/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product/productEdit";
    }

    @PostMapping("/edit")
    public String edit(Product product){
        productService.update(product);
        return "redirect:/product/list";
    }



}
