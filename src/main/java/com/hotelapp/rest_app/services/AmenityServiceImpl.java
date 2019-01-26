package com.hotelapp.rest_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelapp.rest_app.models.Amenity;
import com.hotelapp.rest_app.repositories.AmenityRepository;

@Service
public class AmenityServiceImpl implements AmenityService {
	
	@Autowired
	AmenityRepository amenityRepository;

	@Override
	public Amenity addAmenity(Amenity amenity) {
		return amenityRepository.save(amenity);
	}

	@Override
	public List<Amenity> findAll(String shortDescription) {
		return amenityRepository.findByShortDescriptionContains(shortDescription);
	}

}
