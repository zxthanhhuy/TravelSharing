package vn.edu.iuh.fit.travelsharing.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "TRAVELED_TO")
public class Traveling {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Place place;
	private String startDate;
	
	public Traveling() {
	}

	public Traveling(Long id, User user, Place place, String startDate) {
		this.id = id;
		this.user = user;
		this.place = place;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Traveling)) {
			return false;
		}

		Traveling traveling = (Traveling) o;

		if (startDate != traveling.startDate) {
			return false;
		}
		if (place != null ? !place.equals(traveling.place) : traveling.place != null) {
			return false;
		}
		if (user != null ? !user.equals(traveling.user) : traveling.user != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = user != null ? user.hashCode() : 0;
		result = 31 * result + (place != null ? place.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		return result;
	}
}
