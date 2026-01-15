package com.travel.dto;

import com.travel.entity.TravelPackage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelPackageDTO {
    
    private Long id;
    
    @NotBlank(message = "Package name is required")
    private String name;
    
    @NotBlank(message = "Destination is required")
    private String destination;
    
    private String description;
    
    @NotNull(message = "Duration is required")
    private Integer durationDays;
    
    @NotNull(message = "Price is required")
    private BigDecimal price;
    
    private Integer availableSeats;
    
    private TravelPackage.PackageType packageType;
    
    private String inclusions;
    
    private String exclusions;
    
    private Boolean active;
    
    private String imageUrl;
}
