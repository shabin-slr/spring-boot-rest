package com.hotelapp.rest_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Hotel addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

	@Override
	public Page<Hotel> findHotels(String name, String cityCode, Pageable pageable) {
		// TODO Auto-generated method stub
		return hotelRepository.findByNameContainingAndCityCodeContaining(name, cityCode, pageable);
	}
	
}
