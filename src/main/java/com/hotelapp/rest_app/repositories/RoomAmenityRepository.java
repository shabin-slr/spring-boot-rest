package com.hotelapp.rest_app.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.hotelapp.rest_app.models.Amenity;
import com.hotelapp.rest_app.models.Room;
import com.hotelapp.rest_app.models.RoomAmenity;

public interface RoomAmenityRepository extends CrudRepository<RoomAmenity, Long> {
	
	Set<RoomAmenity> findAllByRoom(Room room);

	RoomAmenity findOneByRoomAndAmenity(Room room, Amenity amenity);

}
