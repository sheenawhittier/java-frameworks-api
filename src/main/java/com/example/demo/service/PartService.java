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
    public List<Part> findAll();
    public Part findById(int theId);
    public void savePart(Part thePart);  // Updated save method to include validation
    public void deleteById(int theId);

    public List<Part> listAll(String keyword);

    // New method for validation
    public void validatePart(Part part);
}

