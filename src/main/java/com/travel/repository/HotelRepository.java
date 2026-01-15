package com.travel.repository;

import com.travel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
    List<Hotel> findByActiveTrue();
    
    List<Hotel> findByLocation(String location);
    
    List<Hotel> findByStarRating(Integer starRating);
    
    List<Hotel> findByLocationContainingIgnoreCase(String location);
    
    List<Hotel> findByActiveTrueAndAvailableRoomsGreaterThan(Integer rooms);
}
