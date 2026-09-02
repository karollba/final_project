package pl.visa.finalproject.product;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
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




    // zmein aby po zeskanowaniu/ wpisaniu barcode uzupelnilo automatcznie wszystkie pola. po co masz pisac recznie jak moze sie samo wy7pelnic
    // zrob tu flasha aby wyswietlal co jest nie tak
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Product product,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productAdd";
        }
        productService.add(product);
        return "redirect:/product/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        Product product = productService.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "product/productEdit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Product product,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productEdit";
        }
        productService.update(product);
        return "redirect:/product/list";
    }


    // barcode

    @GetMapping("/scan")
    public String scanForm() {
        return "product/productScan";
    }

    @PostMapping("/scan")
    public String scan(@RequestParam String barcode, Model model) {
        Optional<Product> product = productService.findByBarcode(barcode);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product/productAdd";
        } else {
            Product newProduct = new Product();
            newProduct.setBarcode(barcode);
            model.addAttribute("product", newProduct);
            return "product/productAdd";
        }
    }


}
