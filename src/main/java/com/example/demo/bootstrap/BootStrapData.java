package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (partRepository.count() == 0 && productRepository.count() == 0) {


            InhousePart part1 = new InhousePart();
//        part1.setId(100);
            part1.setName("Brush#1");
            part1.setPrice(10);
            part1.setInv(5);
            part1.setMin(2);
            part1.setMax(10);
            part1.setPartId(1234);

            InhousePart part2 = new InhousePart();
//        part2.setId(101);
            part2.setName("Brush#2");
            part2.setPrice(10);
            part2.setInv(5);
            part2.setMin(2);
            part2.setMax(10);
            part2.setPartId(1234);

            OutsourcedPart part3 = new OutsourcedPart();
//        part3.setId(102);
            part3.setName("Brush#3");
            part3.setPrice(10);
            part3.setInv(5);
            part3.setMin(2);
            part3.setMax(10);
            part3.setCompanyName("Fatality");

            OutsourcedPart part4 = new OutsourcedPart();
//        part4.setId(103);
            part4.setName("Brush#4");
            part4.setPrice(10);
            part4.setInv(5);
            part4.setMin(2);
            part4.setMax(10);
            part4.setCompanyName("Fatality");

            OutsourcedPart part5 = new OutsourcedPart();
//        part5.setId(104);
            part5.setName("Brush#5");
            part5.setPrice(10);
            part5.setInv(5);
            part5.setMin(2);
            part5.setMax(10);
            part5.setCompanyName("Fatality");

            partRepository.save(part1);
            partRepository.save(part2);
            partRepository.save(part3);
            partRepository.save(part4);
            partRepository.save(part5);
       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
            List<Part> parts = (List<Part>) partRepository.findAll();
            for (Part part : parts) {
                System.out.println(part.getName() + " " + part.getInv());
            }


            Product product1 = new Product("Hair Color Kit", 100.0, 15);
            Product product2 = new Product("Styling Kit", 100.0, 15);
            Product product3 = new Product("Beauty Treatment", 100.0, 15);
            Product product4 = new Product("Red Color Kit", 100.0, 15);
            Product product5 = new Product("Green Color Kit", 100.0, 15);
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);


            System.out.println("Started in Bootstrap");
            System.out.println("Number of Products" + productRepository.count());
            System.out.println(productRepository.findAll());
            System.out.println("Number of Parts" + partRepository.count());
            System.out.println(partRepository.findAll());
        }

    }
}
