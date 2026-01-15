package com.travel.service;

import com.travel.dto.BookingDTO;
import com.travel.entity.Booking;
import com.travel.entity.TravelPackage;
import com.travel.entity.User;
import com.travel.repository.BookingRepository;
import com.travel.repository.TravelPackageRepository;
import com.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TravelPackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        TravelPackage travelPackage = packageRepository.findById(bookingDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));
        
        if (travelPackage.getAvailableSeats() < bookingDTO.getNumberOfPeople()) {
            throw new RuntimeException("Not enough seats available");
        }
        
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTravelPackage(travelPackage);
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setTravelDate(bookingDTO.getTravelDate());
        booking.setNumberOfPeople(bookingDTO.getNumberOfPeople());
        
        BigDecimal totalAmount = travelPackage.getPrice()
                .multiply(BigDecimal.valueOf(bookingDTO.getNumberOfPeople()));
        booking.setTotalAmount(totalAmount);
        booking.setSpecialRequests(bookingDTO.getSpecialRequests());
        
        // Update available seats
        travelPackage.setAvailableSeats(travelPackage.getAvailableSeats() - bookingDTO.getNumberOfPeople());
        packageRepository.save(travelPackage);
        
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDTO(savedBooking);
    }

    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return convertToDTO(booking);
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByStatus(Booking.BookingStatus status) {
        return bookingRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingDTO updateBookingStatus(Long id, Booking.BookingStatus status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        booking.setStatus(status);
        
        if (status == Booking.BookingStatus.CANCELLED) {
            // Return seats to package
            TravelPackage travelPackage = booking.getTravelPackage();
            travelPackage.setAvailableSeats(
                travelPackage.getAvailableSeats() + booking.getNumberOfPeople()
            );
            packageRepository.save(travelPackage);
        }
        
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    @Transactional
    public BookingDTO updatePaymentStatus(Long id, Booking.PaymentStatus paymentStatus) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        booking.setPaymentStatus(paymentStatus);
        
        if (paymentStatus == Booking.PaymentStatus.PAID) {
            booking.setStatus(Booking.BookingStatus.CONFIRMED);
        }
        
        Booking updatedBooking = bookingRepository.save(booking);
        return convertToDTO(updatedBooking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        // Return seats to package
        TravelPackage travelPackage = booking.getTravelPackage();
        travelPackage.setAvailableSeats(
            travelPackage.getAvailableSeats() + booking.getNumberOfPeople()
        );
        packageRepository.save(travelPackage);
        
        bookingRepository.delete(booking);
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = modelMapper.map(booking, BookingDTO.class);
        dto.setUserId(booking.getUser().getId());
        dto.setPackageId(booking.getTravelPackage().getId());
        dto.setUserName(booking.getUser().getName());
        dto.setPackageName(booking.getTravelPackage().getName());
        dto.setDestination(booking.getTravelPackage().getDestination());
        return dto;
    }
}
