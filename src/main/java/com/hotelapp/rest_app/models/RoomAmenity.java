package com.hotelapp.rest_app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="room_amenities")
public class RoomAmenity implements Serializable {

	private static final long serialVersionUID = 1933731164156855158L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_ame_id")
	private Long id;
	
	@NotNull
	@Column(name = "amenity_id")
	private Amenity amenity;
	
	@NotNull
	@Column(name = "room_id")
	@JsonBackReference
	private Room room;
	
	private Boolean chargeble;
	
	private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Amenity getAmenity() {
		return amenity;
	}

	public void setAmenity(Amenity amenity) {
		this.amenity = amenity;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Boolean getChargeble() {
		return chargeble;
	}

	public void setChargeble(Boolean chargeble) {
		this.chargeble = chargeble;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
