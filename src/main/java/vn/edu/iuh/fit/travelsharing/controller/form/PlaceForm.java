package vn.edu.iuh.fit.travelsharing.controller.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.neo4j.ogm.annotation.Relationship;

import vn.edu.iuh.fit.travelsharing.entity.Creating;
import vn.edu.iuh.fit.travelsharing.entity.Relationships;
import vn.edu.iuh.fit.travelsharing.entity.User;

public class PlaceForm {
	private String id;
	
	private String placeId;
	@NotNull
	private String placeName;
	@NotNull
	private String city;
	@NotNull
	private String country;
	private String placeImage;
	private String description;
	
	private Creating ownerUser = new Creating();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPlaceImage() {
		return placeImage;
	}
	
	public void setPlaceImage(String placeImage) {
		this.placeImage = placeImage;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Creating getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(Creating ownerUser) {
		this.ownerUser = ownerUser;
	}

	@Override
	public String toString() {
		return "PlaceForm [id=" + id + ", placeId=" + placeId + ", placeName="
				+ placeName + ", city=" + city + ", country=" + country + "]";
	}
}
