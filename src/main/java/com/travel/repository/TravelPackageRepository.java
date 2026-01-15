package com.travel.repository;

import com.travel.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    
    List<TravelPackage> findByActiveTrue();
    
    List<TravelPackage> findByDestination(String destination);
    
    List<TravelPackage> findByPackageType(TravelPackage.PackageType packageType);
    
    List<TravelPackage> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    List<TravelPackage> findByDestinationContainingIgnoreCase(String destination);
    
    List<TravelPackage> findByActiveTrueAndAvailableSeatsGreaterThan(Integer seats);
}
