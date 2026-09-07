package pl.visa.finalproject.product;

import com.google.zxing.NotFoundException;
import com.google.zxing.qrcode.decoder.Mode;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.visa.finalproject.barcode.BarcodeService;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final BarcodeService barcodeService;
    private final ProductBatchService productBatchService;

    public ProductController(ProductService productService, BarcodeService barcodeService, ProductBatchService productBatchService) {
        this.productService = productService;
        this.barcodeService = barcodeService;
        this.productBatchService = productBatchService;
    }


    @GetMapping("/list")
    public String listProducts(@RequestParam(required = false) String category,
            @RequestParam(required = false) String expiryFilter,
            Model model) {

        List<ProductBatch> products = productBatchService.findFiltered(category, expiryFilter);
        model.addAttribute("products", products);
        model.addAttribute("productCategories", ProductCategory.values());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedExpiryFilter", expiryFilter);

        return "product/productList";
    }

    // poszukujac filtruj napierw przez kategorie potem szukaj uuid bo tak to zajedziesz baze danych


    // najpierw sprawdz czy produkt juz nie istnieje jesli istnieje to dodaj do istniejacego rekordu juz a jak nie istnieje to utworz nowy rekord
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productCategories", ProductCategory.values());
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
    public String scan(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
        try {
            String barcode = barcodeService.decodeBarcode(file);
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
        } catch (NotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Nie rozpoznano kodu kreskowego");
            return "redirect:/product/add";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Błąd odczytu pliku");
            return "redirect:/product/add";
        }

    }

    // dodawanie nowej partii (temrminy)
    @GetMapping("/addbacth")
    public String addBatchForm(Model model) {
        model.addAttribute("batch", new ProductBatch());
        model.addAttribute("products", productService.findAll());
        return "product/batchadd";
    }

    @PostMapping("/addbatch")
    public String addBatch(@Valid @ModelAttribute ProductBatch product,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/porudctBatchAdd";
        }
        productBatchService.add(product);
        return "redirect:/product/list";
    }
}
