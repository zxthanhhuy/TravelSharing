package vn.edu.iuh.fit.travelsharing.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import vn.edu.iuh.fit.travelsharing.controller.form.PlaceForm;
import vn.edu.iuh.fit.travelsharing.entity.Place;
import vn.edu.iuh.fit.travelsharing.repository.PlaceRepository;
import vn.edu.iuh.fit.travelsharing.repository.UserRepository;
import vn.edu.iuh.fit.travelsharing.service.util.GenericCRUDService;

@Service
public class PlaceService extends GenericCRUDService<Place, PlaceForm> {
	final static Logger LOGGER = LoggerFactory.getLogger(PlaceService.class);
	
	@Autowired
	PlaceRepository placeRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public GraphRepository<Place> getRepository() {
		return placeRepository;
	}

	@Override
	public void convertToForm(Place place, PlaceForm form) {
		form.setId(place.getId().toString());
		form.setPlaceId(place.getPlaceId().toString());
		form.setPlaceName(place.getPlaceName());
		form.setCity(place.getCity());
		form.setCountry(place.getCountry());
	}

	@Override
	public Place convertToEntity(PlaceForm form) {
		Place place = new Place();
		if (StringUtils.isNotEmpty(form.getId())) {
			place.setId(Long.valueOf(form.getId()));
		} else {
			place.setId(null); // new node
		}
		if (StringUtils.isNotEmpty(form.getPlaceId())) {
			place.setPlaceId(Integer.parseInt(form.getPlaceId()));
		} else {
			Integer maxId = maxEntityID();
			maxId = maxId == null ? 1 : (maxId + 1);
			place.setPlaceId(maxId); // new node
		}
		place.setPlaceName(form.getPlaceName());
		place.setCity(form.getCity());
		place.setCountry(form.getCountry());
	    return place;
	}

	// Load key-value object to combobox
	@Override
	public Iterable<Map<String, Object>> entityIDs() {
		return placeRepository.placeIDs();
	}

	@Override
	public Integer maxEntityID() {
		return placeRepository.maxPlaceId();
	}
	
	
}
