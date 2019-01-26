package com.hotelapp.rest_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.repositories.HotelRepository;
import com.hotelapp.rest_app.repositories.RoomRepository;

@Service
public class RoomsServiceImpl implements RoomsService {
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	HotelRepository hotelRepository;
	
	public Room addRoom(Room room) {	
		return roomRepository.save(room);
	}

}