package com.hotelapp.rest_app.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.Room;

public interface RoomRepository extends PagingAndSortingRepository<Room,Long>{
	
	List<Room> findByHotel(Hotel hotel);

	Room findOneById(Long roomId);
}
