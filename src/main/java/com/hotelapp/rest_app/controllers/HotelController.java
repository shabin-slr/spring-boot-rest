package com.hotelapp.rest_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.HotelAmenity;
import com.hotelapp.rest_app.repositories.HotelAmenityRepository;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.repositories.RoomRepository;
import com.hotelapp.rest_app.services.HotelService;

@RestController
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	HotelAmenityRepository hotelAmenityRepository;
	
	@Autowired
	RoomRepository roomRepository;

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
	
	@GetMapping(path = "/api/hotels/{hotel_id}")
	public ResponseEntity<Hotel> getHotel(
		@PathVariable(value = "hotel_id") Long hotelId
	){
		Hotel hotel = hotelRepository.findOneById(hotelId);
		if(hotel == null) {
			return ResponseEntity.notFound().build();
		}
		hotel.setRooms(roomRepository.findByHotel(hotel));
		hotel.setAmenities(hotelAmenityRepository.findAllByHotel(hotel));
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
	
	@PutMapping(path = "/api/hotels/{hotel_id}/amenities")
	public ResponseEntity<?> addAmenity(
		@PathVariable(value = "hotel_id") Long hotelId,
		@RequestBody HotelAmenity hotelAmenity
	){
		Hotel hotel = hotelRepository.findOneById(hotelId);
		if(hotel == null) {
			return ResponseEntity.notFound().build();
		}
		HotelAmenity existing = hotelAmenityRepository.findOneByHotelAndAmenity(hotel, hotelAmenity.getAmenity());
		if(existing != null) {
			return ResponseEntity.badRequest().build();
		}
		hotelAmenity.setHotel(hotel);
		hotel = hotelService.addAmenity(hotel, hotelAmenity);
		return ResponseEntity.ok().build();
		//return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
}
