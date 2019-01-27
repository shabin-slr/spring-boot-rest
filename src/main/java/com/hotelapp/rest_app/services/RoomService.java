package com.hotelapp.rest_app.services;

import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.models.RoomAmenity;

public interface RoomService {
	
	public Room addRoom(Room room);

	public Room addAmenity(Room room, RoomAmenity roomAmenity);

}
