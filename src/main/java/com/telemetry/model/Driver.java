package com.telemetry.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String identificationCard;
    private Long vehicleId;
    
    public Driver() {	
    }
    
    public Driver(String firstName, String lastName, String identificationCard, Long vehicleId) {
    	this.firstName = firstName;
    	this.lastName = lastName;
		this.identificationCard = identificationCard;
		this.vehicleId = vehicleId;
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIdentificationCard() {
		return identificationCard;
	}
	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", identificationCard="
				+ identificationCard + ", vehicleId=" + vehicleId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, identificationCard, lastName, vehicleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(identificationCard, other.identificationCard)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(vehicleId, other.vehicleId);
	}
}
