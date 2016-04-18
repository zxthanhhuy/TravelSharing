package vn.edu.iuh.fit.travelsharing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import vn.edu.iuh.fit.travelsharing.entity.Rating;
import vn.edu.iuh.fit.travelsharing.entity.User;

@Repository
public interface UserRepository extends GraphRepository<User> {
	@Query("MATCH (user:User) RETURN MAX(user.userId)")
	Integer maxUserId();

	@Query("MATCH (user:User) RETURN user.userId AS userId, user.email AS email ORDER BY user.email ASC")
	Iterable<Map<String, Object>> userIDs();

	@Query("MATCH (user:User) WHERE user.email = {0} AND user.password = {1} RETURN user")
	User getUserByEmailAndPassword(String email, String password);

	@Query("MATCH (user:User) WHERE user.email = {0} AND user.password = {1} RETURN ID(user)")
	Long getUserNodeIdByEmailAndPassword(String email, String password);

	@Query("MATCH (user:User {email: {0}})-[r:RATED]->(place:Place) "
			+ "RETURN r")
	List<Rating> findRatedPlaces(String email);

	@Query("MATCH (u:User) WHERE u.userId = {0} RETURN u LIMIT 1")
	User findByUserId(String userId);
}
