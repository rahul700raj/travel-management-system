package com.travel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "travel_packages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Package name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Destination is required")
    @Column(nullable = false)
    private String destination;

    @Column(length = 2000)
    private String description;

    @NotNull(message = "Duration is required")
    @Column(nullable = false)
    private Integer durationDays;

    @NotNull(message = "Price is required")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer availableSeats = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PackageType packageType = PackageType.STANDARD;

    @Column(length = 1000)
    private String inclusions;

    @Column(length = 1000)
    private String exclusions;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(length = 500)
    private String imageUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum PackageType {
        STANDARD, DELUXE, PREMIUM, BUDGET
    }
}
