package com.hotelapp.rest_app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hotelapp.rest_app.models.Amenity;

public interface AmenityRepository extends CrudRepository<Amenity, Long> {

	List<Amenity> findByShortDescriptionContains(String shortDescription);

}
