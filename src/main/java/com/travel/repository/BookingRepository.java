package com.travel.repository;

import com.travel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByUserId(Long userId);
    
    List<Booking> findByTravelPackageId(Long packageId);
    
    List<Booking> findByStatus(Booking.BookingStatus status);
    
    List<Booking> findByPaymentStatus(Booking.PaymentStatus paymentStatus);
    
    List<Booking> findByTravelDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Booking> findByUserIdAndStatus(Long userId, Booking.BookingStatus status);
}
