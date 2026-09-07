package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.delivery.DeliveryService;

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
        return "ordered/orderedList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        return "ordered/add";
    }

    @PostMapping("/add")
    public String add(OrderedProduct orderedProduct) {
        orderedProductService.add(orderedProduct);
        return "redirect:/ordered/list";
    }

    @GetMapping("/check")
    public String checkForm(@RequestParam UUID deliveryId, Model model) {
        Delivery delivery = deliveryService.findById(deliveryId).orElseThrow();
        List<OrderedProduct> items = orderedProductService.findBYDelivery(delivery);

        model.addAttribute("items", items);
        model.addAttribute("deliveryId", deliveryId);
        return "ordered/check";
    }

    // do zastanowienia jeszcze to roziwazanie (te dodawanie id)
    @PostMapping("/updatequantity")
    public String updateQuantity(@RequestParam UUID id,
                                 @RequestParam UUID deliveryId,
                                 @RequestParam double recievedQuantity, RedirectAttributes redirectAttributes) {
        orderedProductService.updateRecievedQuantity(id, recievedQuantity);
        redirectAttributes.addFlashAttribute("deliveryId", deliveryId);
        return "redirect:/ordered/check";
    }


}
