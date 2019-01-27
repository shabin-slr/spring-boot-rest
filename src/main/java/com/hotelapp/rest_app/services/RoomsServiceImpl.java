package com.hotelapp.rest_app.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.models.RoomAmenity;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.repositories.RoomAmenityRepository;
import com.hotelapp.rest_app.repositories.RoomRepository;

@Service
public class RoomsServiceImpl implements RoomService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomAmenityRepository roomAmenityRepository;
	
	public Room addRoom(Room room) {	
		return roomRepository.save(room);
	}

	@Override
	public Room addAmenity(Room room, RoomAmenity roomAmenity) {
		
		roomAmenity.setRoom(room);
		roomAmenity = roomAmenityRepository.save(roomAmenity);	
		
		if(CollectionUtils.isEmpty(room.getAmenities())){
			room.setAmenities(new HashSet<RoomAmenity>());
		}
		room.getAmenities().add(roomAmenity);
		
		return room;
	}

}
