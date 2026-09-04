package pl.visa.finalproject.delivery;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }


    @GetMapping("/list")
    public String listDeliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.findAll());
        return "delivery/deliveryList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("delivery", new Delivery());
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

}
