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
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            addSampleInventory();
        }
    }

    private void addSampleInventory() {
        InhousePart part1 = new InhousePart();
        part1.setName("Color Tube");
        part1.setPrice(10.00);
        part1.setInv(50);
        part1.setMin(10);
        part1.setMax(100);
        part1.setPartId(123);

        InhousePart part2 = new InhousePart();
        part2.setName("Developer");
        part2.setPrice(5.00);
        part2.setInv(50);
        part2.setMin(10);
        part2.setMax(100);
        part2.setPartId(124);

        // Continue similarly for other parts...

        partRepository.save(part1);
        partRepository.save(part2);
        //...
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
        validateInventory(theProduct);
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

    // Step 5: Validate Inventory
    private void validateInventory(Product theProduct) {
        for (Part part : theProduct.getParts()) {
            if (part.getInv() < part.getMin() || part.getInv() > part.getMax()) {
                throw new IllegalArgumentException("Inventory for part '" + part.getName() + "' must be between "
                        + part.getMin() + " and " + part.getMax() + ".");
            }
        }
    }
}






