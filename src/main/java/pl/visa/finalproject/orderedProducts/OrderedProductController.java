package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/check")
    public String checkForm(@RequestParam UUID deliveryId, Model model) {
        Delivery delivery = deliveryService.findById(deliveryId).orElseThrow();
        List<OrderedProduct> items = orderedProductService.findBYDelivery(delivery);

        model.addAttribute("items", items);
        model.addAttribute("deliveryId", deliveryId);
        return "orderedproduct/check";
    }

//    @PostMapping("/updateQuantity")
//    public String updateQuantity(@RequestParam UUID id, @RequestParam UUID deliveryId, @RequestParam double recievedQuantity) {
//        orderedProductService.updateRecievedQuantity(id, recievedQuantity);
//        return "redirect:/orderedproduct/check/"
//    }

}
