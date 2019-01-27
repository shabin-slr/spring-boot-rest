package com.hotelapp.rest_app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.HotelAmenity;

public interface HotelService {

	Hotel addHotel(Hotel hotel);
	
	Page<Hotel> findHotels(String name, String cityCode, Pageable pageable);

	Hotel addAmenity(Hotel hotel, HotelAmenity hotelAmenity);
	
}
