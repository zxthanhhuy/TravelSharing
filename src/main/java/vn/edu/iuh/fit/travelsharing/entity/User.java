package vn.edu.iuh.fit.travelsharing.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity(label = "User")
public class User {
	@GraphId
	private Long id;

	@Property(name = "userId")
	private Integer userId;

	private String email;
	private String password;
	private String name;
	private String birthdate;
	private String birthplace;
	private String profileImage;
	private String phone;
	private String about;

//	@Relationship(type = Relationships.FOLLOWED, direction = Relationship.INCOMING)
//	private Set<Following> followers = new HashSet<>();

	@Relationship(type = Relationships.FOLLOWED, direction = Relationship.OUTGOING)
	private Set<Following> followings = new HashSet<>();

	@Relationship(type = Relationships.LIVED_AT, direction = Relationship.OUTGOING)
	private Set<Living> livings = new HashSet<>();

	@Relationship(type = Relationships.TRAVELED_TO, direction = Relationship.OUTGOING)
	private Set<Traveling> travelings = new HashSet<>();

	@Relationship(type = Relationships.RATED, direction = Relationship.OUTGOING)
	private Set<Rating> ratings = new HashSet<>();

	@Relationship(type = Relationships.CREATED, direction = Relationship.OUTGOING)
	private Set<Creating> creatings = new HashSet<>();

	public User() {
	}

	public User(Integer userId, String email, String password, String name,
			String birthdate, String birthplace, String profileImage,
			String phone) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.birthdate = birthdate;
		this.birthplace = birthplace;
		this.profileImage = profileImage;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<Following> getFollowings() {
		return followings;
	}

	public void setFollowings(Set<Following> followings) {
		this.followings = followings;
	}

//	public Set<Following> getFollowers() {
//		return followers;
//	}
//
//	public void setFollowers(Set<Following> followers) {
//		this.followers = followers;
//	}

	public Set<Living> getLivings() {
		return livings;
	}

	public void setLivings(Set<Living> livings) {
		this.livings = livings;
	}

	public Set<Traveling> getTravelings() {
		return travelings;
	}

	public void setTravelings(Set<Traveling> travelings) {
		this.travelings = travelings;
	}

	public Set<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

	public Set<Creating> getCreatings() {
		return creatings;
	}

	public void setCreatings(Set<Creating> creatings) {
		this.creatings = creatings;
	}

	public Creating createPlace(Place place) {
		Creating creating = new Creating(this, place);
		creatings.add(creating);
		return creating;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User user = (User) o;

		if (email != null ? !email.equals(user.email) : user.email != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return email != null ? email.hashCode() : 0;
	}
}