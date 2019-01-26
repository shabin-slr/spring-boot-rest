package com.hotelapp.rest_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Amenity;
import com.hotelapp.rest_app.services.AmenityService;

@RestController
public class AmenitiesController {
	
	@Autowired
	AmenityService amenityService;
	
	@PostMapping(path = "/api/admin/amenities")
	public ResponseEntity<Amenity> addHotel(@RequestBody Amenity amenity) {
		Amenity savedAmenity = amenityService.addAmenity(amenity);
		return new ResponseEntity<Amenity>(savedAmenity, HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "/api/amenities")
	public ResponseEntity<List<Amenity>> getAmenities(
		@RequestParam(value = "name", defaultValue = "") String shortDescription
	){
		return new ResponseEntity<List<Amenity>>(amenityService.findAll(shortDescription), HttpStatus.ACCEPTED);
	}
}
