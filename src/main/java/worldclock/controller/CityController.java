package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.City;
import worldclock.service.CityService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/getCitiesByName/{cityName}")
	public List<City> getCitesByName(@PathVariable String cityName) {
		
		return cityService.getCitesByName(cityName);
		
	}
	
	@RequestMapping("/getCities/{cityName}")
	public List<City> searchCityWithExactName(@PathVariable String cityName) {
		
		return cityService.searchCityWithExactName(cityName);
		
	}
}
