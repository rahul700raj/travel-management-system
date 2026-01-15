package com.travel.controller;

import com.travel.dto.TravelPackageDTO;
import com.travel.service.TravelPackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
@Tag(name = "Travel Package Management", description = "APIs for managing travel packages")
public class TravelPackageController {

    private final TravelPackageService packageService;

    @PostMapping
    @Operation(summary = "Create a new travel package")
    public ResponseEntity<TravelPackageDTO> createPackage(@Valid @RequestBody TravelPackageDTO packageDTO) {
        TravelPackageDTO createdPackage = packageService.createPackage(packageDTO);
        return new ResponseEntity<>(createdPackage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get package by ID")
    public ResponseEntity<TravelPackageDTO> getPackageById(@PathVariable Long id) {
        TravelPackageDTO travelPackage = packageService.getPackageById(id);
        return ResponseEntity.ok(travelPackage);
    }

    @GetMapping
    @Operation(summary = "Get all packages")
    public ResponseEntity<List<TravelPackageDTO>> getAllPackages() {
        List<TravelPackageDTO> packages = packageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active packages")
    public ResponseEntity<List<TravelPackageDTO>> getActivePackages() {
        List<TravelPackageDTO> packages = packageService.getActivePackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/available")
    @Operation(summary = "Get available packages with seats")
    public ResponseEntity<List<TravelPackageDTO>> getAvailablePackages() {
        List<TravelPackageDTO> packages = packageService.getAvailablePackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/search")
    @Operation(summary = "Search packages by destination")
    public ResponseEntity<List<TravelPackageDTO>> searchByDestination(@RequestParam String destination) {
        List<TravelPackageDTO> packages = packageService.searchByDestination(destination);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/price-range")
    @Operation(summary = "Get packages by price range")
    public ResponseEntity<List<TravelPackageDTO>> getPackagesByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<TravelPackageDTO> packages = packageService.getPackagesByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(packages);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update package")
    public ResponseEntity<TravelPackageDTO> updatePackage(
            @PathVariable Long id,
            @Valid @RequestBody TravelPackageDTO packageDTO) {
        TravelPackageDTO updatedPackage = packageService.updatePackage(id, packageDTO);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete package (soft delete)")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }
}
