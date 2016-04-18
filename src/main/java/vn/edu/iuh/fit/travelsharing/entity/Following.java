package vn.edu.iuh.fit.travelsharing.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "FOLLOWED")
public class Following {
	@GraphId
	private Long id;
	@StartNode
	private User startUser;
	@EndNode
	private User endUser;
	private String startDate;
	
	public Following() {
	}

	public Following(Long id, User startUser, User endUser, String startDate) {
		this.id = id;
		this.startUser = startUser;
		this.endUser = endUser;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStartUser() {
		return startUser;
	}

	public void setStartUser(User startUser) {
		this.startUser = startUser;
	}

	public User getEndUser() {
		return endUser;
	}

	public void setEndUser(User endUser) {
		this.endUser = endUser;
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
		if (!(o instanceof Following)) {
			return false;
		}

		Following following = (Following) o;

		if (startDate != following.startDate) {
			return false;
		}
		if (startUser != null ? !startUser.equals(following.startUser) : following.startUser != null) {
			return false;
		}
		if (endUser != null ? !endUser.equals(following.endUser) : following.endUser != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = startUser != null ? startUser.hashCode() : 0;
		result = 31 * result + (endUser != null ? endUser.hashCode() : 0);
		result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
		return result;
	}
}
