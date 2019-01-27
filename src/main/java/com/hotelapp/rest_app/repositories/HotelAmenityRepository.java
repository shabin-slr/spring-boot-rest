package com.hotelapp.rest_app.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.hotelapp.rest_app.models.Amenity;
import com.hotelapp.rest_app.models.Hotel;
import com.hotelapp.rest_app.models.HotelAmenity;

public interface HotelAmenityRepository extends CrudRepository<HotelAmenity, Long> {

	Set<HotelAmenity> findAllByHotel(Hotel hotel);

	HotelAmenity findOneByHotelAndAmenity(Hotel hotel, Amenity Amenity);
}
