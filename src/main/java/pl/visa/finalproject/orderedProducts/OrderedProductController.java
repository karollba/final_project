package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.delivery.DeliveryService;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductCategory;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orderedproduct")
public class OrderedProductController {

    private final OrderedProductService orderedProductService;
    private final DeliveryService deliveryService;

    public OrderedProductController(OrderedProductService orderedProductService, DeliveryService deliveryService) {
        this.orderedProductService = orderedProductService;
        this.deliveryService = deliveryService;
    }

    @GetMapping("/list")
    public String listOrderedProducts(Model model) {
        model.addAttribute("orderedProducts", orderedProductService.findAll());
        return "orderedproduct/orderedproductList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        return "orderedproduct/orderedproductAdd";
    }

    @PostMapping("/add")
    public String add(OrderedProduct orderedProduct) {
        orderedProductService.add(orderedProduct);
        return "redirect:/orderedproduct/list";
    }

    @GetMapping("/check")
    public String checkForm(@RequestParam UUID deliveryId, Model model) {
        Delivery delivery = deliveryService.findById(deliveryId).orElseThrow();
        List<OrderedProduct> items = orderedProductService.findBYDelivery(delivery);

        model.addAttribute("items", items);
        model.addAttribute("deliveryId", deliveryId);
        return "orderedproduct/check";
    }

    // do zastanowienia jeszcze to roziwazanie (te dodawanie id)
    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam UUID id,
                                 @RequestParam UUID deliveryId,
                                 @RequestParam double recievedQuantity, RedirectAttributes redirectAttributes) {
        orderedProductService.updateRecievedQuantity(id, recievedQuantity);
        redirectAttributes.addFlashAttribute("deliveryId", deliveryId);
        return "redirect:/orderedproduct/check";
    }

}
