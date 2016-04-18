package vn.edu.iuh.fit.travelsharing.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class PlaceRecommendation {
	private String placeId;
	private String placeName;
	private String city;
	private String country;
//	String tagline;

	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

//	public String getTagline() {
//		return tagline;
//	}
//
//	public void setTagline(String tagline) {
//		this.tagline = tagline;
//	}
}
