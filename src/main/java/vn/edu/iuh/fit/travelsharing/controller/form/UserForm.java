package vn.edu.iuh.fit.travelsharing.controller.form;

import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class UserForm {
	private String id;
	
	@DecimalMin("0")
	private String userId;
	@NotNull(message = "Email must be not empty")
	private String email;
	@NotNull(message = "Password must be not empty")
	private String password;
	@NotNull(message = "Confirm password must be matched with password")
	private String confirmPassword;
	@NotNull(message = "Name must be not empty")
	private String name;
	@NotNull(message = "Birthdate must be not empty")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private String birthdate;
	@NotNull(message = "Birthplace must be not empty")
	private String birthplace;
	private String profileImage;
	private String phone;
	private String about;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
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
		checkPassword();
	}

	public String getConfirmPassword() {
		return password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		checkPassword();
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}

	private void checkPassword() {
		if (this.password == null || this.confirmPassword == null) {
			return;
		} else if (!this.password.equals(confirmPassword)) {
			this.confirmPassword = null;
		}
	}
}
