package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainScreenControllerr {

    private final PartService partService;
    private final ProductService productService;

    @Autowired
    public MainScreenControllerr(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String listPartsandProducts(Model theModel, @RequestParam(value = "partkeyword", required = false) String partkeyword,
                                       @RequestParam(value = "productkeyword", required = false) String productkeyword) {
        // Fetch parts based on the search keyword
        List<Part> partList = partService.listAll(partkeyword);
        theModel.addAttribute("parts", partList);
        theModel.addAttribute("partkeyword", partkeyword);

        // Fetch products based on the search keyword
        List<Product> productList = productService.listAll(productkeyword);
        theModel.addAttribute("products", productList);
        theModel.addAttribute("productkeyword", productkeyword);

        return "mainscreen";
    }

    @PostMapping("/product/buy")
    public String buyProduct(@RequestParam("productId") Long productId, Model model) {
        Product product = productService.findById(productId.intValue());

        if (product != null) {
            if (product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productService.save(product);
                model.addAttribute("message", "Purchase successful! Inventory decremented by 1.");
            } else {
                model.addAttribute("message", "Purchase failed! Product is out of stock.");
            }
        } else {
            model.addAttribute("message", "Purchase failed! Product not found.");
        }

        // Refresh the product list to show all products, including those with zero inventory
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "mainscreen"; // Return to the main screen
    }

    @PostMapping("/savePart")
    public String savePart(@Valid @ModelAttribute("part") Part part, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "partForm"; // Return to the part form with validation errors
        }
        try {
            partService.save(part);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "partForm"; // Display the error message on the form
        }
        return "redirect:/mainscreen"; // Redirect back to the main screen after successful save
    }

    @GetMapping("/About")
    public String showAboutPage() {
        return "About";  // This will return the About.html template from the templates folder
    }
}


