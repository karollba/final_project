package pl.visa.finalproject.product;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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



    // zrob tu flasha aby wyswietlal co jest nie tak
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productAdd";
        }
        productService.add(product);
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
