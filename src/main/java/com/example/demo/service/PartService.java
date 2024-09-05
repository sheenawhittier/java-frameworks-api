package com.example.demo.service;

import com.example.demo.domain.Part;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface PartService {
    List<Part> findAll();
    Part findById(int theId);
    void save(Part thePart);  // Updated save method to include validation
    void deleteById(int theId);
    List<Part> listAll(String keyword);

}

