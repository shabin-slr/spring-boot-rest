package com.hotelapp.rest_app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.hotelapp.rest_app.models.Hotel;

@RestResource(exported = false)
public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long>{
	
	Page<Hotel> findAllByCityCode(String cityCode, Pageable pageable);
	
	Page<Hotel> findByNameContainingAndCityCodeContaining(String name, String cityCode, Pageable pageable);

}
