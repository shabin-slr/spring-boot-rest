package com.hotelapp.rest_app.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.HotelAmenity;
import com.hotelapp.rest_app.repositories.HotelAmenityRepository;
import com.hotelapp.rest_app.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	HotelAmenityRepository hotelAmenityRepository;
	
	@Override
	public Hotel addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

	@Override
	public Page<Hotel> findHotels(String name, String cityCode, Pageable pageable) {
		return hotelRepository.findByNameContainingAndCityCodeContaining(name, cityCode, pageable);
	}

	@Override
	public Hotel addAmenity(Hotel hotel, HotelAmenity hotelAmenity) {
		
		hotelAmenity.setHotel(hotel);
		
		hotelAmenity = hotelAmenityRepository.save(hotelAmenity);
		
		if(CollectionUtils.isEmpty(hotel.getAmenities())) {
			hotel.setAmenities(new HashSet<HotelAmenity>());
		}
		
		hotel.getAmenities().add(hotelAmenity);
		
		return hotel;
	}
	
}
