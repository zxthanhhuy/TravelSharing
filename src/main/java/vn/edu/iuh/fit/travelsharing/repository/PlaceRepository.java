package vn.edu.iuh.fit.travelsharing.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import vn.edu.iuh.fit.travelsharing.entity.Place;
import vn.edu.iuh.fit.travelsharing.entity.PlaceRecommendation;
import vn.edu.iuh.fit.travelsharing.entity.User;

@Repository
public interface PlaceRepository extends GraphRepository<Place> {
	@Query("MATCH (place:Place) RETURN MAX(place.placeId)")
	Integer maxPlaceId();

	@Query("MATCH (p) RETURN p.placeId AS placeId, p.placeName AS placeName ORDER BY p.placeName ASC")
	Iterable<Map<String, Object>> placeIDs();

	@Query("match (user:User {email: {0}})-[r:RATED]->(place)<-[r2:RATED]-(other)-[r3:RATED]->(otherPlace) "
			+ " where r.stars >= 3 and r2.stars >= r.stars and r3.stars >= r.stars "
			+ " and not((user)-[:RATED]->(otherPlace)) "
			+ " with otherPlace, toInt(round(avg(r3.stars))) as rating, count(*) as cnt"
			+ " order by rating desc, cnt desc"
			+ " return otherPlace.placeId as placeId, otherPlace.placeName as placeName, otherPlace.city, otherPlace.country, rating as rating limit 10")
	List<PlaceRecommendation> getRecommendations(String email);

}
