package pl.visa.finalproject.orderedProducts;

import org.hibernate.query.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.visa.finalproject.delivery.Delivery;
import pl.visa.finalproject.delivery.DeliveryService;
import pl.visa.finalproject.product.ProductBatch;
import pl.visa.finalproject.product.ProductBatchService;
import pl.visa.finalproject.product.ProductService;
import pl.visa.finalproject.supplier.SupplierService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderedProductController {

    private final OrderedProductService orderedProductService;
    private final DeliveryService deliveryService;
    private final ProductBatchService productBatchService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final ProductOrderService productOrderService;

    public OrderedProductController(OrderedProductService orderedProductService, DeliveryService deliveryService, ProductBatchService productBatchService, SupplierService supplierService, ProductService productService, ProductOrderService productOrderService) {
        this.orderedProductService = orderedProductService;
        this.deliveryService = deliveryService;
        this.productBatchService = productBatchService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.productOrderService = productOrderService;
    }


    @GetMapping("/list")
    public String listOrderedProducts(Model model) {
        model.addAttribute("productOrderedProducts", productOrderService.findAll());
        return "ordered/orderList";
    }

    @GetMapping("/show")
    public String showDetails(Model model) {
        model.addAttribute("productOrder", productOrderService.findAll());
        return "ordered/orderList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        return "ordered/orderAdd";
    }

    @PostMapping("/add")
    public String add(OrderedProduct orderedProduct) {
        orderedProductService.add(orderedProduct);
        return "redirect:/order/list";
    }

    @GetMapping("/check")
    public String checkForm(@RequestParam UUID deliveryId, Model model) {
        Delivery delivery = deliveryService.findById(deliveryId).orElseThrow();
        List<OrderedProduct> items = orderedProductService.findBYDelivery(delivery);

        model.addAttribute("items", items);
        model.addAttribute("deliveryId", deliveryId);
        return "order/check";
    }

    // do zastanowienia jeszcze to roziwazanie (te dodawanie id)
    @PostMapping("/updatequantity")
    public String updateQuantity(@RequestParam UUID id,
                                 @RequestParam UUID orderId,
                                 @RequestParam double recievedQuantity,
                                 @RequestParam LocalDate expirationDate,
                                 RedirectAttributes redirectAttributes) {

        Optional<OrderedProduct> item = Optional.of(orderedProductService.findById(id).orElseThrow());

        ProductBatch batch = new ProductBatch();
        batch.setDelivery(item.get().getDelivery());
        batch.setProduct(item.get().getProduct());
        batch.setQuantity(recievedQuantity);
        batch.setExpirationDate(expirationDate);
        batch.setDeliveryDate(LocalDateTime.now());
        productBatchService.add(batch);

        orderedProductService.updateRecievedQuantity(id, recievedQuantity);
        return "redirect:/productorder/show?id=" + orderId;
    }


}
