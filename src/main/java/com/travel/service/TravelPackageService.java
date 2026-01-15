package com.travel.service;

import com.travel.dto.TravelPackageDTO;
import com.travel.entity.TravelPackage;
import com.travel.repository.TravelPackageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelPackageService {

    private final TravelPackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public TravelPackageDTO createPackage(TravelPackageDTO packageDTO) {
        TravelPackage travelPackage = modelMapper.map(packageDTO, TravelPackage.class);
        TravelPackage savedPackage = packageRepository.save(travelPackage);
        return modelMapper.map(savedPackage, TravelPackageDTO.class);
    }

    public TravelPackageDTO getPackageById(Long id) {
        TravelPackage travelPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        return modelMapper.map(travelPackage, TravelPackageDTO.class);
    }

    public List<TravelPackageDTO> getAllPackages() {
        return packageRepository.findAll().stream()
                .map(pkg -> modelMapper.map(pkg, TravelPackageDTO.class))
                .collect(Collectors.toList());
    }

    public List<TravelPackageDTO> getActivePackages() {
        return packageRepository.findByActiveTrue().stream()
                .map(pkg -> modelMapper.map(pkg, TravelPackageDTO.class))
                .collect(Collectors.toList());
    }

    public List<TravelPackageDTO> getAvailablePackages() {
        return packageRepository.findByActiveTrueAndAvailableSeatsGreaterThan(0).stream()
                .map(pkg -> modelMapper.map(pkg, TravelPackageDTO.class))
                .collect(Collectors.toList());
    }

    public List<TravelPackageDTO> searchByDestination(String destination) {
        return packageRepository.findByDestinationContainingIgnoreCase(destination).stream()
                .map(pkg -> modelMapper.map(pkg, TravelPackageDTO.class))
                .collect(Collectors.toList());
    }

    public List<TravelPackageDTO> getPackagesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return packageRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(pkg -> modelMapper.map(pkg, TravelPackageDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public TravelPackageDTO updatePackage(Long id, TravelPackageDTO packageDTO) {
        TravelPackage travelPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        
        travelPackage.setName(packageDTO.getName());
        travelPackage.setDestination(packageDTO.getDestination());
        travelPackage.setDescription(packageDTO.getDescription());
        travelPackage.setDurationDays(packageDTO.getDurationDays());
        travelPackage.setPrice(packageDTO.getPrice());
        travelPackage.setAvailableSeats(packageDTO.getAvailableSeats());
        travelPackage.setPackageType(packageDTO.getPackageType());
        travelPackage.setInclusions(packageDTO.getInclusions());
        travelPackage.setExclusions(packageDTO.getExclusions());
        travelPackage.setImageUrl(packageDTO.getImageUrl());
        
        TravelPackage updatedPackage = packageRepository.save(travelPackage);
        return modelMapper.map(updatedPackage, TravelPackageDTO.class);
    }

    @Transactional
    public void deletePackage(Long id) {
        TravelPackage travelPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        travelPackage.setActive(false);
        packageRepository.save(travelPackage);
    }
}
