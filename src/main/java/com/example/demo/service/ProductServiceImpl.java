package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PartRepository partRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PartRepository partRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
    }

    @PostConstruct
    public void init() {

        }



    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Long theIdl = (long) theId;
        Optional<Product> result = productRepository.findById(theIdl);

        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        } else {
            throw new RuntimeException("Did not find product id - " + theId);
        }

        return theProduct;
    }

    @Override
    public void save(Product theProduct) {
        validateProductInventory(theProduct); // Validate inventory before saving
        productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        Long theIdl = (long) theId;
        productRepository.deleteById(theIdl);
    }

    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return (List<Product>) productRepository.findAll();
    }

    // Validation for Product affecting Part inventory
    private void validateProductInventory(Product theProduct) {
        for (Part part : theProduct.getParts()) {
            if (part.getInv() < part.getMin()) {
                throw new IllegalArgumentException("Inventory for part '" + part.getName() + "' cannot be less than the minimum allowed (" + part.getMin() + ").");
            }
            if (part.getInv() > part.getMax()) {
                throw new IllegalArgumentException("Inventory for part '" + part.getName() + "' cannot be greater than the maximum allowed (" + part.getMax() + ").");
            }
        }
    }
}







