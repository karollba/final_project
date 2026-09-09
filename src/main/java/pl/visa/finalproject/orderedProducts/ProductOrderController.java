package pl.visa.finalproject.orderedProducts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.visa.finalproject.delivery.DeliveryService;
import pl.visa.finalproject.product.Product;
import pl.visa.finalproject.product.ProductBatch;
import pl.visa.finalproject.product.ProductBatchService;
import pl.visa.finalproject.product.ProductService;
import pl.visa.finalproject.supplier.Supplier;
import pl.visa.finalproject.supplier.SupplierService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/productorder")
public class ProductOrderController {

    private final OrderedProductService orderedProductService;
    private final SupplierService supplierService;
    private final ProductService productService;
    private final ProductOrderService productOrderService;
    private final ProductBatchService productBatchService;

    public ProductOrderController(OrderedProductService orderedProductService, SupplierService supplierService, ProductService productService, ProductOrderService productOrderService, ProductBatchService productBatchService) {
        this.orderedProductService = orderedProductService;
        this.supplierService = supplierService;
        this.productService = productService;
        this.productOrderService = productOrderService;
        this.productBatchService = productBatchService;
    }

    @GetMapping("/list")
    public String listOrderedProducts(Model model) {
        model.addAttribute("orders", productOrderService.findAll());
        return "ordered/orderList";
    }

    @GetMapping("/show")
    public String showOrder(@RequestParam UUID id, Model model) {
        ProductOrder order = productOrderService.findById(id).orElseThrow();
        List<OrderedProduct> items = orderedProductService.findByProductOrder(order);

        model.addAttribute("order", order);
        model.addAttribute("items", items);

        return "ordered/orderShow";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        return "ordered/orderAdd";
    }

    @PostMapping("/add")
    public String add(@RequestParam UUID supplierId,
                      @RequestParam List<UUID> productsIds,
                      @RequestParam List<Double> orderedQuantities) {

        Supplier supplier = supplierService.findById(supplierId).orElseThrow();
        ProductOrder order = productOrderService.createOrder(supplier);

        for (int i = 0; i < productsIds.size(); i++) {
            Product product = productService.findById(productsIds.get(i)).orElseThrow();

            OrderedProduct item = new OrderedProduct();
            item.setProductOrder(order);
            item.setProduct(product);
            item.setOrderedQuantity(orderedQuantities.get(i));
            item.setUnit(product.getDefaultUnit());
            item.setChecked(false);

            orderedProductService.add(item);
        }
        return "redirect:/productorder/list";
    }

    @PostMapping("/updatequantity")
    public String updateQuantity(@RequestParam UUID id,
                                 @RequestParam UUID deliveryId,
                                 @RequestParam double recievedQuantity,
                                 @RequestParam LocalDate expirationDate,
                                 RedirectAttributes redirectAttributes) {

        OrderedProduct item = orderedProductService.findById(id).orElseThrow();

        ProductBatch batch = new ProductBatch();
        batch.setProduct(item.getProduct());
        batch.setQuantity(recievedQuantity);
        batch.setExpirationDate(expirationDate);
        batch.setDeliveryDate(LocalDateTime.now());
        productBatchService.add(batch);

        orderedProductService.updateRecievedQuantity(id, recievedQuantity);
        redirectAttributes.addAttribute("deliveryId", deliveryId);
        return "redirect:/order/check";
    }
}