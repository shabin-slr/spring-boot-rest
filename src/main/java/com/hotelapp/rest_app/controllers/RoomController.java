package com.hotelapp.rest_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.models.RoomAmenity;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.repositories.RoomAmenityRepository;
import com.hotelapp.rest_app.repositories.RoomRepository;
import com.hotelapp.rest_app.services.HotelService;
import com.hotelapp.rest_app.services.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	RoomAmenityRepository roomAmenityRepository;

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
		savedRoom = roomService.addRoom(room);
		room.setHotel(hotel);
		return new ResponseEntity<Room>(savedRoom, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/api/hotels/{hotel_id}/rooms")
	public ResponseEntity<?> getRooms(
		@PathVariable(value = "hotel_id") Long hotelId
	){
		Hotel hotel = hotelRepository.findOneById(hotelId);
		if( hotel == null) {
			return ResponseEntity.notFound().build();
		}
		List<Room> rooms = roomRepository.findByHotel(hotel);
		rooms.stream().forEach(room ->{
			room.setAmenities(roomAmenityRepository.findAllByRoom(room));
			//room.setHotel(hotel);
		});
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	/*
	 * @PutMapping(path = "/api/rooms/{room_id}/amenities") public
	 * ResponseEntity<Room> addAmenity(
	 * 
	 * @PathVariable(value = "room_id") Long roomId,
	 * 
	 * @RequestBody RoomAmenity roomAmenity ){ Room room =
	 * roomRepository.findOneById(roomId); if(room == null) { return
	 * ResponseEntity.notFound().build(); } room = roomService.addAmenity(room,
	 * roomAmenity); return new ResponseEntity<Room>(room, HttpStatus.OK); }
	 */
	
	@PutMapping(path = "/api/rooms/{room_id}/amenities")
	public ResponseEntity<?> addAmenity(
		@PathVariable(value = "room_id") Long roomId,
		@RequestBody RoomAmenity roomAmenity
	){
		Room room = roomRepository.findOneById(roomId);
		if(room == null) {
			return ResponseEntity.notFound().build();
		}
		RoomAmenity existing = roomAmenityRepository.findOneByRoomAndAmenity(room, roomAmenity.getAmenity());
		if(existing != null) {
			//return ResponseEntity.badRequest().build();
			roomAmenity.setId(existing.getId());
		}
		roomAmenity.setRoom(room);
		room = roomService.addAmenity(room, roomAmenity);
		return ResponseEntity.ok().build();
		//return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
	}
}


