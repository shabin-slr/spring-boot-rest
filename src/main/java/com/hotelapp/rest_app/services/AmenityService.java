package com.hotelapp.rest_app.services;

import java.util.List;

import com.hotelapp.rest_app.models.Amenity;

public interface AmenityService {

	Amenity addAmenity(Amenity amenity);

	List<Amenity> findAll(String shortDescription);

}
