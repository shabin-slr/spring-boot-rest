package com.hotelapp.rest_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.services.HotelService;

@RestController
public class HotelController {
	
	@Autowired
	HotelService hotelService;

	@PostMapping(path = "/api/hotels")
	public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelService.addHotel(hotel);
		return new ResponseEntity<Hotel>(savedHotel, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/api/hotels")
	public ResponseEntity<Page<Hotel>> findHotels(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "city", defaultValue="") String cityCode,
			@PageableDefault(size = 5,value = 0
	) Pageable pageable){
		return new ResponseEntity<Page<Hotel>>(hotelService.findHotels(name, cityCode, pageable), HttpStatus.OK);
	}
}
