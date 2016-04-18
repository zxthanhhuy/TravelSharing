package vn.edu.iuh.fit.travelsharing.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "RATED")
public class Rating {
	@GraphId
	private Long id;
	@StartNode
	private User user;
	@EndNode
	private Place place;
	private String startDate;
	private int stars;
	private String comment;

	public Rating() {
	}

	public Rating(User user, Place place, int stars, String comment) {
		this.setUser(user);
		this.setPlace(place);
		this.setStars(stars);
		this.setComment(comment);
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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		if (!(o instanceof Rating)) {
			return false;
		}

		Rating rating = (Rating) o;

		if (stars != rating.stars) {
			return false;
		}
		if (comment != null ? !comment.equals(rating.comment)
				: rating.comment != null) {
			return false;
		}
		if (place != null ? !place.equals(rating.place) : rating.place != null) {
			return false;
		}
		if (user != null ? !user.equals(rating.user) : rating.user != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = user != null ? user.hashCode() : 0;
		result = 31 * result + (place != null ? place.hashCode() : 0);
		result = 31 * result + stars;
		result = 31 * result + (comment != null ? comment.hashCode() : 0);
		return result;
	}
}
