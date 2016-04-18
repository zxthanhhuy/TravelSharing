package vn.edu.iuh.fit.travelsharing.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "CREATED")
public class Creating {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Place place;
	private String startDate;

	public Creating() {
	}
	
	public Creating(User user, Place place) {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy H:mm:ss");
		this.startDate = dateTime.format(formatter);
		this.user = user;
		this.place = place;
	}
	
	public Creating(User user, Place place, String startDate) {
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
		if (!(o instanceof Creating)) {
			return false;
		}

		Creating creating = (Creating) o;

		if (startDate != creating.startDate) {
			return false;
		}
		if (place != null ? !place.equals(creating.place)
				: creating.place != null) {
			return false;
		}
		if (user != null ? !user.equals(creating.user) : creating.user != null) {
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
