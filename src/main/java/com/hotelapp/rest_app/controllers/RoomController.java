package com.hotelapp.rest_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.models.RoomAmenity;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.repositories.RoomRepository;
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
	
	@Autowired
	RoomRepository roomRepository;

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
	
	@PutMapping(path = "/api/rooms/{room_id}/amenities")
	public ResponseEntity<Room> addAmenity(
		@PathVariable(value = "room_id") Long roomId,
		@RequestBody RoomAmenity roomAmenity
	){
		Room room = roomRepository.findOneById(roomId);
		if(room == null) {
			return ResponseEntity.notFound().build();
		}
		room = roomsService.addAmenity(room, roomAmenity);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
}


