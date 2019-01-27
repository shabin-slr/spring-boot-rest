package com.hotelapp.rest_app.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="rooms")
public class Room implements Serializable {

	private static final long serialVersionUID = -3104859604942985271L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	@NotNull
	private Hotel hotel;
	
	@Transient
	@JsonManagedReference
	private Set<RoomAmenity> amenities;
	
	@Column(name="description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<RoomAmenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<RoomAmenity> amenities) {
		this.amenities = amenities;
	}
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", hotel=" + hotel + ", description=" + description + "]";
	}

}
