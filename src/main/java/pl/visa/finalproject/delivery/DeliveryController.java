package pl.visa.finalproject.delivery;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.visa.finalproject.orderedProducts.OrderedProduct;
import pl.visa.finalproject.orderedProducts.OrderedProductService;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductService;
import pl.visa.finalproject.supplier.Supplier;
import pl.visa.finalproject.supplier.SupplierService;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final OrderedProductService orderedProductService;

    public DeliveryController(DeliveryService deliveryService, SupplierService supplierService, ProductService productService, OrderedProductService orderedProductService) {
        this.deliveryService = deliveryService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.orderedProductService = orderedProductService;
    }


    @GetMapping("/list")
    public String listDeliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.findAll());
        return "delivery/deliveryList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("delivery", new Delivery());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("deliveryCategories", DeliveryCategory.values());
        return "delivery/deliveryAdd";
    }

    @PostMapping("/add")
    public String  add(@Valid @ModelAttribute Delivery delivery,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println("Errror" + error.toString()));
            return "delivery/deliveryAdd";
        }

        deliveryService.save(delivery);
        return "redirect:/delivery/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        Delivery delivery = deliveryService.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        return "delivery/deliveryEdit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Delivery delivery, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "delivery/deliveryEdit";
        }
        deliveryService.update(delivery);
        return "redirect:/delivery/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Supplier.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.isEmpty()) {
                    UUID uuid = UUID.fromString(text);
                    Supplier supplier = supplierService.findById(uuid).orElse(null);
                    setValue(supplier);
                }
            }
        });
    }


    // lista produktow

    @GetMapping("/addwithitems")
    public String addOrderForm(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        return "ordered/orderAdd";
    }

    // przemysl to jeszcze bo jest troche nagmatwane

    @PostMapping("/addwithitems")
    public String addOrderWithItems(@RequestParam UUID supplierId,
                                    @RequestParam List<UUID> productsIds,
                                    @RequestParam List<Double> orderQuantities) {
        Supplier supplier = supplierService.findById(supplierId).orElseThrow();
        Delivery delivery = new Delivery();

        delivery.setSupplier(supplier);
        delivery.setDateOfAcceptTheDelivery(null);
        deliveryService.add(delivery);

        for (int i = 0; i < productsIds.size(); i++) {
            Product product = productService.findById(productsIds.get(i)).orElseThrow();

            OrderedProduct item = new OrderedProduct();
            orderedProductService.add(item);

        }

        return "redirect:/delivery/list";
    }

}
