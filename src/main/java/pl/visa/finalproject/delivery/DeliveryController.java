package pl.visa.finalproject.delivery;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.visa.finalproject.employee.Employee;
import pl.visa.finalproject.orderedProducts.OrderedProduct;
import pl.visa.finalproject.orderedProducts.OrderedProductService;
import pl.visa.finalproject.orderedProducts.ProductOrder;
import pl.visa.finalproject.orderedProducts.ProductOrderService;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductService;
import pl.visa.finalproject.supplier.Supplier;
import pl.visa.finalproject.supplier.SupplierService;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final SupplierService supplierService;
    private final ProductOrderService productOrderService;

    public DeliveryController(DeliveryService deliveryService, SupplierService supplierService, ProductOrderService productOrderService) {
        this.deliveryService = deliveryService;
        this.supplierService = supplierService;
        this.productOrderService = productOrderService;
    }


    @GetMapping("/list")
    public String listDeliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.findAll());
        return "delivery/deliveryList";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam UUID id) {
        deliveryService.delete(id);
        return "redirect:/delivery/list";
    }


    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("deliveries", deliveryService.search(query));
        model.addAttribute("query", query);
        return "delivery/deliveryList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("delivery", new Delivery());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("deliveryCategories", DeliveryCategory.values());
        model.addAttribute("orders", productOrderService.findUnassignedOrders());
        return "delivery/deliveryAdd";
    }

    @PostMapping("/add")
    public String  add(@Valid @ModelAttribute Delivery delivery,
                       BindingResult bindingResult,
                       @RequestParam(required = false) UUID orderId,
                       @AuthenticationPrincipal Employee loggedInEmployee, Model model) {

        if (orderId == null) {
            model.addAttribute("error", "Wybierz zamówienie!");
            model.addAttribute("suppliers", supplierService.findAll());
            model.addAttribute("deliveryCategories", DeliveryCategory.values());
            model.addAttribute("orders", productOrderService.findAll());
            return "delivery/deliveryAdd";
        }
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> System.out.println("Błąd: " + objectError.toString()));
            model.addAttribute("suppliers", supplierService.findAll());
            model.addAttribute("deliveryCategories", DeliveryCategory.values());
            model.addAttribute("orders", productOrderService.findAll());
            return "delivery/deliveryAdd";
        }
        ProductOrder order = productOrderService.findById(orderId).orElseThrow();
        delivery.setSupplier(order.getSupplier());
        delivery.setDeliveryId(order.getOrderNumber());
        delivery.setAcceptingEmployee(loggedInEmployee);

        deliveryService.save(delivery);
        order.setDelivery(delivery);
        productOrderService.save(order);

        return "redirect:/delivery/list";
    }

    @GetMapping("/showorder")
    public String showOrderDelivery(@RequestParam UUID deliveryId) {
        Delivery delivery = deliveryService.findById(deliveryId).orElseThrow();
        ProductOrder order = productOrderService.findByDelivery(delivery).orElseThrow();
        return "redirect:/productorder/show?id=" + order.getId();
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        Delivery delivery = deliveryService.findById(id).orElseThrow();
        model.addAttribute("delivery", delivery);
        model.addAttribute("deliveryCategories", DeliveryCategory.values());
        return "delivery/deliveryEdit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Delivery delivery, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("delivery", delivery);
            return "delivery/deliveryEdit";
        }
        deliveryService.update(delivery);
        return "redirect:/delivery/list";
    }


    // zamienia tekst na UUID, szuka w bazie i ustawia jako wartosc.
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

}
