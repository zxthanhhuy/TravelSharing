package vn.edu.iuh.fit.travelsharing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.iuh.fit.travelsharing.entity.User;
import vn.edu.iuh.fit.travelsharing.service.UserService;

public class UserTest {

	@Test
	public void testLogin() {
		User tmp = new UserService().getUserByEmailAndPassword(
				"zxthanhhuy@gmail.com", "12345");
		//assertTrue(tmp != null);
	}
}
