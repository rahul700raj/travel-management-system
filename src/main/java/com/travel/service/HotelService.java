package com.travel.service;

import com.travel.dto.HotelDTO;
import com.travel.entity.Hotel;
import com.travel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        Hotel savedHotel = hotelRepository.save(hotel);
        return modelMapper.map(savedHotel, HotelDTO.class);
    }

    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        return modelMapper.map(hotel, HotelDTO.class);
    }

    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    public List<HotelDTO> getActiveHotels() {
        return hotelRepository.findByActiveTrue().stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    public List<HotelDTO> searchByLocation(String location) {
        return hotelRepository.findByLocationContainingIgnoreCase(location).stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    public List<HotelDTO> getAvailableHotels() {
        return hotelRepository.findByActiveTrueAndAvailableRoomsGreaterThan(0).stream()
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        
        hotel.setName(hotelDTO.getName());
        hotel.setLocation(hotelDTO.getLocation());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setStarRating(hotelDTO.getStarRating());
        hotel.setPricePerNight(hotelDTO.getPricePerNight());
        hotel.setAmenities(hotelDTO.getAmenities());
        hotel.setTotalRooms(hotelDTO.getTotalRooms());
        hotel.setAvailableRooms(hotelDTO.getAvailableRooms());
        hotel.setImageUrl(hotelDTO.getImageUrl());
        
        Hotel updatedHotel = hotelRepository.save(hotel);
        return modelMapper.map(updatedHotel, HotelDTO.class);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        hotel.setActive(false);
        hotelRepository.save(hotel);
    }
}
