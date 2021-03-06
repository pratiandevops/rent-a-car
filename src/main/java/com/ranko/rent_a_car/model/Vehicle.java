package com.ranko.rent_a_car.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="vehicles")
public class Vehicle {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@NotEmpty
	private String carBrand;

	@NotEmpty
	private String carModel;

	private Integer numberOfSeats;

	private String color;

	private String note;

	private BigDecimal pricePerDay;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(BigDecimal pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	//Check if this is for New of Update
	public boolean isNew() {
		return (this.id == null);
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"carBrand='" + carBrand + '\'' +
				", carModel='" + carModel + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}
