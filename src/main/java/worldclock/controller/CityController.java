package worldclock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import worldclock.entity.City;
import worldclock.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping("/getCitesByName/{cityName}")
	public List<City> getCitesByName(@PathVariable String cityName) {
		return cityService.getCitesByName(cityName);
	}
}
