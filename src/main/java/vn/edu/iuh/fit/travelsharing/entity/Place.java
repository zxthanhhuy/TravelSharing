package vn.edu.iuh.fit.travelsharing.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "Place")
public class Place {
	@GraphId
	private Long id;

	@Property(name = "placeId")
	private Integer placeId;
	private String placeName;
	private String city;
	private String country;
	private String description;
	private String placeImage;
	
	@Relationship(type = Relationships.LIVED_AT, direction = Relationship.INCOMING)
	private Set<User> livedUsers = new HashSet<>();
	
	@Relationship(type = Relationships.TRAVELED_TO, direction = Relationship.INCOMING)
	private Set<User> traveledUsers = new HashSet<>();
	
	@Relationship(type = Relationships.RATED, direction = Relationship.INCOMING)
	private Set<User> ratedUsers = new HashSet<>();
	
	public Place() {
	}
	
	public Place(Integer placeId, String city, String country) {
		this.placeId = placeId;
		this.city = city;
		this.country = country;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaceImage() {
		return placeImage;
	}

	public void setPlaceImage(String placeImage) {
		this.placeImage = placeImage;
	}

	public Set<User> getLivedUsers() {
		return livedUsers;
	}

	public void setLivedUsers(Set<User> livedUsers) {
		this.livedUsers = livedUsers;
	}

	public Set<User> getTraveledUsers() {
		return traveledUsers;
	}

	public void setTraveledUsers(Set<User> traveledUsers) {
		this.traveledUsers = traveledUsers;
	}

	public Set<User> getRatedUsers() {
		return ratedUsers;
	}

	public void setRatedUsers(Set<User> ratedUsers) {
		this.ratedUsers = ratedUsers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Place)) {
			return false;
		}

		Place place = (Place) o;

		if (placeId != null ? !placeId.equals(place.placeId)
				: place.placeId != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return placeId != null ? placeId.hashCode() : 0;
	}
}
