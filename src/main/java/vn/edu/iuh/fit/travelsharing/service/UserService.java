package vn.edu.iuh.fit.travelsharing.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import vn.edu.iuh.fit.travelsharing.controller.form.UserForm;
import vn.edu.iuh.fit.travelsharing.entity.Rating;
import vn.edu.iuh.fit.travelsharing.entity.User;
import vn.edu.iuh.fit.travelsharing.repository.UserRepository;
import vn.edu.iuh.fit.travelsharing.service.util.GenericCRUDService;

@Service
public class UserService extends GenericCRUDService<User, UserForm> {
	final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Override
	public GraphRepository<User> getRepository() {
		return userRepository;
	}

	@Override
	public void convertToForm(User user, UserForm form) {
		form.setId(user.getId().toString());
		form.setUserId(user.getUserId().toString());
		form.setEmail(user.getEmail());
		form.setPassword(user.getPassword());
		form.setName(user.getName());
		form.setBirthdate(user.getBirthdate());
		form.setBirthplace(user.getBirthplace());
		form.setProfileImage(user.getProfileImage());
		form.setPhone(user.getPhone());
		
		if (user.getCreatings() != null) {
		      user.getCreatings().stream().forEach(p ->{
		        //form.get.put(Integer.valueOf(p.getProductID()), p.getProductName());
		      });
		    }
	}

	@Override
	public User convertToEntity(UserForm form) {
		User user = new User();

		if (StringUtils.isNotEmpty(form.getId())) {
			user.setId(Long.valueOf(form.getId()));
		} else {
			user.setId(null); // new node
		}
		if (StringUtils.isNotEmpty(form.getUserId())) {
			user.setUserId(Integer.parseInt(form.getUserId()));
		} else {
			int maxId = maxEntityID() == null ? 0 : maxEntityID() + 1;
			user.setUserId(Integer.parseInt(maxId + "")); // new node
		}
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setName(form.getName());
		user.setBirthdate(form.getBirthdate());
		user.setBirthplace(form.getBirthplace());
		user.setProfileImage(ServiceUtils.nvl(form.getProfileImage()));
		user.setPhone(ServiceUtils.nvl(form.getPhone()));
		
		
		return user;
	}
	
	public User save(final User user, final int depth) {
	    return getRepository().save(user, depth);
	  }

	@Override
	public Iterable<Map<String, Object>> entityIDs() {
		return userRepository.userIDs();
	}

	@Override
	public Integer maxEntityID() {
		return userRepository.maxUserId();
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		return userRepository.getUserByEmailAndPassword(email, password);
	}
	
	public Long getUserNodeIdByEmailAndPassword(String email, String password) {
		return userRepository.getUserNodeIdByEmailAndPassword(email, password);
	}
	
	public List<Rating> findRatedPlaces(String email) {
		return userRepository.findRatedPlaces(email);
	}
}
