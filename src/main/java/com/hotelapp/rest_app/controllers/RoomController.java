package com.hotelapp.rest_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.services.HotelService;
import com.hotelapp.rest_app.services.RoomsService;

@RestController
public class RoomController {
	
	@Autowired
	RoomsService roomsService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	HotelRepository hotelRepository;

	@PostMapping(path = "/api/hotels/{hotel_id}/rooms")
	public ResponseEntity<?> addRoom(
			@PathVariable(value = "hotel_id") Long hotelId,
			@RequestBody Room room
	) {
		Room savedRoom;
		Hotel hotel = hotelRepository.findOneById(hotelId);
		
		if( hotel == null) {
			return ResponseEntity.notFound().build();
		}
		room.setHotel(hotel);
		savedRoom = roomsService.addRoom(room);
		room.setHotel(hotel);
		return new ResponseEntity<Room>(savedRoom, HttpStatus.ACCEPTED);
	}
}
