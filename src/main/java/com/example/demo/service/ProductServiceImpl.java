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
        // Create Parts using the no-argument constructor and setters
        InhousePart part1 = new InhousePart();
        part1.setName("Color Tube");
        part1.setPrice(10.00);
        part1.setInv(50); // This sets the inventory level
        part1.setPartId(123); // Assuming this method sets a machine ID or similar identifier

        InhousePart part2 = new InhousePart();
        part2.setName("Developer");
        part2.setPrice(5.00);
        part2.setInv(50);
        part2.setPartId(124);

        InhousePart part3 = new InhousePart();
        part3.setName("Brush");
        part3.setPrice(3.00);
        part3.setInv(50);
        part3.setPartId(125);

        InhousePart part4 = new InhousePart();
        part4.setName("Hair Dryer");
        part4.setPrice(50.00);
        part4.setInv(30);
        part4.setPartId(126);

        InhousePart part5 = new InhousePart();
        part5.setName("Curling Iron");
        part5.setPrice(45.00);
        part5.setInv(30);
        part5.setPartId(127);

        // Save Parts to Inventory
        partRepository.save(part1);
        partRepository.save(part2);
        partRepository.save(part3);
        partRepository.save(part4);
        partRepository.save(part5);

        // Create Products
        Set<Part> product1Parts = new HashSet<>();
        product1Parts.add(part1);
        product1Parts.add(part2);
        product1Parts.add(part3);
        Product product1 = new Product();
        product1.setName("Complete Hair Color Kit");
        product1.setParts(product1Parts);

        Set<Part> product2Parts = new HashSet<>();
        product2Parts.add(part4);
        Product product2 = new Product();
        product2.setName("Hair Dryer");
        product2.setParts(product2Parts);

        Set<Part> product3Parts = new HashSet<>();
        product3Parts.add(part5);
        Product product3 = new Product();
        product3.setName("Curling Iron");
        product3.setParts(product3Parts);

        // Adding a "multi-pack" for duplicate items
        Set<Part> product4Parts = new HashSet<>();
        product4Parts.add(part1);
        product4Parts.add(part1); // Adding duplicate to demonstrate multi-pack
        Product product4 = new Product();
        product4.setName("Color Tube Multi-Pack");
        product4.setParts(product4Parts);

        Set<Part> product5Parts = new HashSet<>();
        product5Parts.add(part2);
        product5Parts.add(part2); // Adding duplicate to demonstrate multi-pack
        Product product5 = new Product();
        product5.setName("Developer Multi-Pack");
        product5.setParts(product5Parts);

        // Save Products to Inventory
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
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
}





