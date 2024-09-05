package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddProductController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PartService partService;

    @Autowired
    private ProductService productService;

    private Product product;

    @GetMapping("/showFormAddProduct")
    public String showFormAddPart(Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        product = new Product();
        theModel.addAttribute("product", product);

        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        theModel.addAttribute("assparts", product.getParts());
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model theModel) {
        theModel.addAttribute("product", product);

        if (bindingResult.hasErrors()) {
            Product product2 = productService.findById((int) product.getId());
            theModel.addAttribute("parts", partService.findAll());
            List<Part> availParts = new ArrayList<>();
            for (Part p : partService.findAll()) {
                if (!product2.getParts().contains(p)) availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            theModel.addAttribute("assparts", product2.getParts());
            return "productForm";
        } else {
            if (product.getId() != 0) {
                Product product2 = productService.findById((int) product.getId());
                if (product.getInv() - product2.getInv() > 0) {
                    for (Part p : product2.getParts()) {
                        int inv = p.getInv();
                        p.setInv(inv - (product.getInv() - product2.getInv()));
                        partService.save(p); // Ensure you use PartService to save parts
                    }
                }
            } else {
                product.setInv(0);
            }
            productService.save(product);
            return "confirmationaddproduct";
        }
    }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int theId, Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        Product theProduct = productService.findById(theId);
        product = theProduct;

        theModel.addAttribute("product", theProduct);
        theModel.addAttribute("assparts", theProduct.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!theProduct.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int theId, Model theModel) {
        Product product2 = productService.findById(theId);
        for (Part part : product2.getParts()) {
            part.getProducts().remove(product2);
            partService.save(part); // Use PartService to save parts
        }
        product2.getParts().clear();
        productService.save(product2);
        productService.deleteById(theId);

        return "confirmationdeleteproduct";
    }

    @GetMapping("/associatepart")
    public String associatePart(@Valid @RequestParam("partID") int theID, Model theModel) {
        if (product == null || product.getName() == null) {
            return "saveproductscreen";
        } else {
            Part part = partService.findById(theID);
            product.getParts().add(part);
            part.getProducts().add(product);
            productService.save(product);
            partService.save(part); // Use PartService to save parts
            theModel.addAttribute("product", product);
            theModel.addAttribute("assparts", product.getParts());
            List<Part> availParts = new ArrayList<>();
            for (Part p : partService.findAll()) {
                if (!product.getParts().contains(p)) availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            return "productForm";
        }
    }

    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int theID, Model theModel) {
        Part part = partService.findById(theID);
        product.getParts().remove(part);
        part.getProducts().remove(product);
        productService.save(product);
        partService.save(part); // Use PartService to save parts
        theModel.addAttribute("product", product);
        theModel.addAttribute("assparts", product.getParts());
        List<Part> availParts = new ArrayList<>();
        for (Part p : partService.findAll()) {
            if (!product.getParts().contains(p)) availParts.add(p);
        }
        theModel.addAttribute("availparts", availParts);
        return "productForm";
    }
}

