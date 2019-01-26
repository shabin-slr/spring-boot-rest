package com.hotelapp.rest_app.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="rooms")
public class Room implements Serializable {

	private static final long serialVersionUID = -3104859604942985271L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private Long id;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "hotel_id", foreignKey = @ForeignKey(name = "FK_USER"))
	private Hotel hotel;
	
	@Column(name="description")
	private String description;

	@Override
	public String toString() {
		return "Room [id=" + id + ", hotel=" + hotel + ", description=" + description + "]";
	}

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

}
