package com.telemetry.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class VehicleMessage {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime timestamp;
    private double longitude;
    private double latitude;
    private int speed;
    private int eventId;
    private Long vehicleId;
    @Transient
    private Map<String, String> ioElements;
    
    public VehicleMessage() {
	}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public Long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Map<String, String> getIoElements() {
		return ioElements;
	}
	public void setIoElements(Map<String, String> ioElements) {
		this.ioElements = ioElements;
	}

	@Override
	public String toString() {
		return "VehicleMessage [id=" + id + ", timestamp=" + timestamp + ", longitude=" + longitude + ", latitude="
				+ latitude + ", speed=" + speed + ", eventId=" + eventId + ", vehicleId=" + vehicleId + ", ioElements="
				+ ioElements + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId, id, ioElements, latitude, longitude, speed, timestamp, vehicleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VehicleMessage other = (VehicleMessage) obj;
		return eventId == other.eventId && Objects.equals(id, other.id) && Objects.equals(ioElements, other.ioElements)
				&& Double.doubleToLongBits(latitude) == Double.doubleToLongBits(other.latitude)
				&& Double.doubleToLongBits(longitude) == Double.doubleToLongBits(other.longitude)
				&& speed == other.speed && Objects.equals(timestamp, other.timestamp)
				&& Objects.equals(vehicleId, other.vehicleId);
	}
}
