package pl.visa.finalproject.supplier;

import jakarta.validation.Valid;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/list")
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return  "supplier/supplierList";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/supplierAdd";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Supplier supplier,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "supplier/supplierAdd";
        }

        supplierService.save(supplier);
        return "redirect:/supplier/list";
    }


    @GetMapping("/edit")
    public String editForm(@RequestParam UUID id, Model model) {
        Supplier supplier = supplierService.findById(id).orElseThrow();
        model.addAttribute("supplier", supplier);
        return "supplier/supplierEdit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Supplier supplier, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "supplier/supplierEdit";
        }
        supplierService.update(supplier);
        return "redirect:/supplier/list";
    }}
