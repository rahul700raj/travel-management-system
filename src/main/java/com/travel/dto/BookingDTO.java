package com.travel.dto;

import com.travel.entity.Booking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    
    private Long id;
    
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotNull(message = "Package ID is required")
    private Long packageId;
    
    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;
    
    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;
    
    @NotNull(message = "Number of people is required")
    private Integer numberOfPeople;
    
    private BigDecimal totalAmount;
    
    private Booking.BookingStatus status;
    
    private Booking.PaymentStatus paymentStatus;
    
    private String specialRequests;
    
    // Additional fields for response
    private String userName;
    private String packageName;
    private String destination;
}
