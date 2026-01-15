package com.travel.controller;

import com.travel.dto.HotelDTO;
import com.travel.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@Tag(name = "Hotel Management", description = "APIs for managing hotels")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @Operation(summary = "Create a new hotel")
    public ResponseEntity<HotelDTO> createHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        HotelDTO createdHotel = hotelService.createHotel(hotelDTO);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by ID")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    @Operation(summary = "Get all hotels")
    public ResponseEntity<List<HotelDTO>> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active hotels")
    public ResponseEntity<List<HotelDTO>> getActiveHotels() {
        List<HotelDTO> hotels = hotelService.getActiveHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/available")
    @Operation(summary = "Get available hotels with rooms")
    public ResponseEntity<List<HotelDTO>> getAvailableHotels() {
        List<HotelDTO> hotels = hotelService.getAvailableHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels by location")
    public ResponseEntity<List<HotelDTO>> searchByLocation(@RequestParam String location) {
        List<HotelDTO> hotels = hotelService.searchByLocation(location);
        return ResponseEntity.ok(hotels);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update hotel")
    public ResponseEntity<HotelDTO> updateHotel(
            @PathVariable Long id,
            @Valid @RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotel(id, hotelDTO);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hotel (soft delete)")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
