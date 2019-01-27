package com.hotelapp.rest_app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "hotels")
public class Hotel implements Serializable {

	private static final long serialVersionUID = 3540795763965490396L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hotel_id")
	private Long id;

	@NotNull
	@Column(name = "hotel_name", unique = true, nullable = false)
	private String name;

	@NotNull
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull
	private @Column(name = "city_code", nullable = false)
	String cityCode;

	//@OneToMany(mappedBy = "hotel")
	@Transient
	@JsonManagedReference
	private List<Room> rooms;
	
	@Transient
	@JsonManagedReference
	private Set<HotelAmenity> amenities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String hotelName) {
		this.name = hotelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public List<Room> getRooms() {
		if(CollectionUtils.isEmpty(rooms)) {
			return new ArrayList<Room>();
		}
		return rooms;
	}
	 
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	} 

	public Set<HotelAmenity> getAmenities() {
		if(CollectionUtils.isEmpty(amenities)) {
			new HashSet<HotelAmenity>();
		}
		return amenities;
	}

	public void setAmenities(Set<HotelAmenity> amenities) {
		this.amenities = amenities;
	}
	
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", description=" + description + ", cityCode=" + cityCode + "]";
	}

}
