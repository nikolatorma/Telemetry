package com.telemetry.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String licensePlate;
    private String vehicleType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", licensePlate=" + licensePlate + ", vehicleType=" + vehicleType + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, licensePlate, vehicleType);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id) && Objects.equals(licensePlate, other.licensePlate)
				&& Objects.equals(vehicleType, other.vehicleType);
	}
}
