package com.travel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    
    private Long id;
    
    @NotBlank(message = "Hotel name is required")
    private String name;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    private String address;
    
    private Integer starRating;
    
    private BigDecimal pricePerNight;
    
    private String amenities;
    
    private Integer totalRooms;
    
    private Integer availableRooms;
    
    private Boolean active;
    
    private String imageUrl;
}
