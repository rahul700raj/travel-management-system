package com.travel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @Column(length = 1000)
    private String address;

    @Column(nullable = false)
    private Integer starRating = 3;

    @Column(precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @Column(length = 2000)
    private String amenities;

    @Column(nullable = false)
    private Integer totalRooms = 0;

    @Column(nullable = false)
    private Integer availableRooms = 0;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(length = 500)
    private String imageUrl;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
