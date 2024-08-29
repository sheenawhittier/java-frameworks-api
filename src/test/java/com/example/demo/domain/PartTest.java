package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:44 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class PartTest {
    Part partIn;
    Part partOut;

    @BeforeEach
    void setUp() {
        partIn = new InhousePart();
        partOut = new OutsourcedPart();
        partIn.setMin(10);
        partIn.setMax(100);
        partOut.setMin(10);
        partOut.setMax(100);
    }

    @Test
    void getId() {
        Long idValue = 4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }

    @Test
    void setId() {
        Long idValue = 4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }

    @Test
    void getName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void setName() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }

    @Test
    void getPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void setPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }

    @Test
    void getInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void setInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }

    @Test
    void getProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void setProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }

    @Test
    void testToString() {
        String name = "test inhouse part";
        partIn.setName(name);
        assertEquals(name, partIn.toString());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.toString());
    }

    @Test
    void testEquals() {
        partIn.setId(1l);
        Part newPartIn = new InhousePart();
        newPartIn.setId(1l);
        assertEquals(partIn, newPartIn);
        partOut.setId(1l);
        Part newPartOut = new OutsourcedPart();
        newPartOut.setId(1l);
        assertEquals(partOut, newPartOut);

    }

    @Test
    void testHashCode() {
        partIn.setId(1l);
        partOut.setId(1l);
        assertEquals(partIn.hashCode(), partOut.hashCode());
    }

    // New Tests for Min and Max Fields
    @Test
    void testInventoryBelowMinThrowsException() {
        partIn.setInv(5); // Below the minimum

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validatePart(partIn);
        });

        String expectedMessage = "Inventory cannot be less than the minimum allowed";
        assertEquals(expectedMessage, exception.getMessage().substring(0, expectedMessage.length()));
    }

    @Test
    void testInventoryAboveMaxThrowsException() {
        partIn.setInv(150); // Above the maximum

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validatePart(partIn);
        });

        String expectedMessage = "Inventory cannot be greater than the maximum allowed";
        assertEquals(expectedMessage, exception.getMessage().substring(0, expectedMessage.length()));
    }

    // Helper method to simulate validation (as would be in PartServiceImpl)
    private void validatePart(Part part) {
        if (part.getInv() < part.getMin()) {
            throw new IllegalArgumentException("Inventory cannot be less than the minimum allowed (" + part.getMin() + ").");
        }
        if (part.getInv() > part.getMax()) {
            throw new IllegalArgumentException("Inventory cannot be greater than the maximum allowed (" + part.getMax() + ").");
        }
    }
}
